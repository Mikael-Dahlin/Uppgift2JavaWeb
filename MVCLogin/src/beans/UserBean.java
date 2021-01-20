package beans;

public class UserBean {
    private String name, password, favoriteSnack;
    // list of users.
    private String users[] = {"Mikael-admin", "Marcus-admin", "User-admin", "Admin-password"};
      
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFavoriteSnack() {
		return favoriteSnack;
	}

	public void setFavoriteSnack(String favoriteFruit) {
		this.favoriteSnack = favoriteFruit;
	}

	public boolean validate(){
		// Check if username and password matches the list of users.
		for(int i = 0 ; i < users.length ; i++) {
			String user[] = users[i].split("-");
			if(name.equals(user[0]) && password.equals(user[1])){  
				return true;  
			}  
		} 
        return false;  
    }  
}
