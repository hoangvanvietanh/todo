package com.green.spring.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

import utils.Constants;

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
		return "profile";
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

	//Download image
	@RequestMapping(value="/avatar/{email}", method = RequestMethod.GET)
    public void downloadAvatar(HttpServletResponse response, @PathVariable("email") String email) throws IOException {
     
    	// get Contact form DB
    	User contact = userService.findByEmail(email);
    	
    	if (contact == null) {
    		return; // no contact found
    	}
    	
    	
    	String fullPath = Constants.UPLOAD_FOLDER + "/" + contact.getAvatar(); 
        File file = new File(fullPath);
         
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
         
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
         
        response.setContentType(mimeType);
         
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
         
        response.setContentLength((int)file.length());
 
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
 
        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
	
}
