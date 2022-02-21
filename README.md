# Group5_CST438_Project01
![](https://i.ibb.co/hLM7CSn/fcode.png)
## Team Members

1. Ignacio Gramajo
2. Ryan Trinh
3. Alexander Ventura
4. Andrew Liddle

## App Description
![](https://i.ibb.co/8g7Y9HW/foodora-bw-sm.jpg)

The foodora app allows users to find, save, and edit recipes, as well as publish recipes under their own user account.
## API Info

We will be using the [Spoonacular API](https://spoonacular.com/food-api). to search for Recipes
The Spoonacular API is accessed by sending HTTPS requests on specific URLs. The Spoonacular Recipe Search API allows us to search through millions of web recipes and integrate this information into our app.

### Note on API Keys
Developers wishing to use this as a base for their own work will need to add an ApiKey class with their own key from Spoontacualr, as shown below:
```
package com.daclink.drew.sp22.cst438_project01_starter.Api;

public class ApiKey {
    private String key;

    public ApiKey(){
        key = "YOUR_KEY_HERE";
    }

    public String getKey() {
        return key;
    }
}
```
## Resources 
- API [Link](https://spoonacular.com/food-api/docs).

## App Workflow
1. User Opens App
2. Create a user
3. Login with user
4. Go to My Recipes
5. Tap on Add Recipes --> Swipe top down to refresh
6. Square button to minimize app
7. Re-open app, select My Recipes --> See that recipes are still there
8. Tap on My Account
9. Update Password
10. Logout of Account
11. Login with new Password

## App Preview
**V1 (Pre-API)**
![](https://user-images.githubusercontent.com/10646650/154596100-b9e37e58-7bcc-4b31-b434-c6467b1d46c7.png)

