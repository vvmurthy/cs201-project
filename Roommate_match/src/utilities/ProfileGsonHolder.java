package utilities;

import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * For holding GSON data
 * @author veda
 *
 */
public class ProfileGsonHolder {

	@Expose
	private List<ProfileInfo> profiles;

	public List<ProfileInfo> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<ProfileInfo> profiles) {
		this.profiles = profiles;
	}
	
}
