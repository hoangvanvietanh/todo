package com.green.spring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.green.spring.entity.User;
import com.green.spring.model.UserModel;
import com.green.spring.service.UserService;

@Controller
@SessionAttributes("email")
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	private static String UPLOADED_FOLDER = "/home/hoangvanvietanh/uploadFile//";

	@RequestMapping(method = RequestMethod.GET)
	public String user(@ModelAttribute("email") String email, Model model) throws ParseException {

		if(email.equals("null"))
		{
			return "redirect:/login"; 
		}
		User users = userService.findByEmail(email);
		UserModel userLogin = new UserModel();
		userLogin.formUser(users);
		model.addAttribute("user", userLogin);
		return "detailUser";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("file") MultipartFile file, @ModelAttribute(name = "user") User user,
			@ModelAttribute("email") String email, Model model, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:/user";
		}

		try {

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		user.setAvatar(file.getOriginalFilename());
		userService.updateUser(user);

		return "redirect:/user";
	}

	@PostMapping("/uploadMulti")
	public String multiFileUpload(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {

		StringJoiner sj = new StringJoiner(" , ");

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue;
			}

			try {

				byte[] bytes = file.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);

				sj.add(file.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		String uploadedFileName = sj.toString();
		if (StringUtils.isEmpty(uploadedFileName)) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
		} else {
			redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + uploadedFileName + "'");
		}

		return "redirect:/login/uploadStatus";

	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}

	@GetMapping("/uploadMultiPage")
	public String uploadMultiPage() {
		return "uploadMulti";
	}

}
