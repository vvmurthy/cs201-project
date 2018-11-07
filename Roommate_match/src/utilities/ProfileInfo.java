package utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64OutputStream;

public class ProfileInfo {
	int userId;
	String email;
	String fullname;
	String profilePicLink;
	ComparisonHolder ch;
	

	public void populate(ResultSet rs) throws SQLException {
		userId = rs.getInt("UserID");
		email = rs.getString("email");
		fullname = rs.getString("fullname");
		try {
			File fl = new File(rs.getString("profile_pic_link"));
			String fileName = fl.getName();
		    FileInputStream fileInputStreamReader = new FileInputStream(fl);
	        byte[] bytes = new byte[(int)fl.length()];
	        fileInputStreamReader.read(bytes);
			profilePicLink = new String(Base64.getEncoder().encode(bytes), "UTF-8");
		}catch(IOException e) {
			System.out.println("could not load image");
		}
	}
	
	public void setComparisonHolder(ComparisonHolder ch) {
		this.ch = ch;
	}
	
	public ComparisonHolder getComparison() {return ch;}

	public int getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getFullname() {
		return fullname;
	}

	public String getProfilePicLink() {
		return profilePicLink;
	}
}