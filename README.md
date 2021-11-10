*HealthyBites*
Original App Design Project - README Template
===

# Nutrition

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Our app will help our users by making it easier to become more informed on topics like nutrition and preparing meals that improves their well being.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='Bite.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).


### App Evaluation
- **Category:** Nutrition
- **Mobile:** For the time being, this application is mobile only.
- **Story:** Recipies, Favorites 
- **Market:** For health enthusiasts and people with dietary concerns
- **Habit:**  This app can be used on a daily basis
- **Scope:**  This app aims to become global! To facilitate people's ability to choose a variety of healthy foods and become more informed about their diet.

Category: Events.
Mobile: For the time being, this application is mobile only.
Story: Home feed, search for events, create event, profile view.
Market: This app are for any people looking to join, search, and create events in their local area.
Habit: This app can be used approximately 1-2 times a week or when you want to go to a new event.
Scope: This app aims to become global! Being able to search and find events from all around the world will open greater opportunities for the user!


## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can register for an account
* User can log in
* User can see different meals
* User can save meals into favorites


**Optional Nice-to-have Stories**
* User can create/edit profile
* Create/Edit favorite meals
* Get detailed view of each meals which inclides recipie and information
* Comparison option betweens meals
* Display portions of ingredients depending on the person
* Different meal uses Example:[weight-loss, muscle gain and more energy]
* Add/edit ingredients to grocery list button

### 2. Screen Archetypes

* Register
   * User can register for an account
* Login
   * User can log in
* Stream
  * User can see different meals 
* Creation
  * User can save meals into favorites  

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home
* Favorites
* Profile

**Flow Navigation** (Screen to Screen)
* Login
  * Home
* Register
  * Home
* Home
  * Detail
    * Creation 

## Wireframes
<img src="HealthyBites_Wireframe.png" width=600>

## Schema 
### User
 | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user (default field) |
   | username      | String   | name of the user |
   | password      | String   | password for login |
   | email         | String   | email for account signup |
   | age           | Number   | age of user |
   | height        | Number   | height of the user |
   | weight        | Number   | weight of user |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
   
   
   ### Favorites
 | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user favorites list (default field) |
   | userId        | Pointer to User| favorites list owner |
   | nameRecipe    | String   | name of the saved recipe |
   | image         | File     | image of the saved recipe|
   | description   | String   | description of the recipe |
   | ingredients   | String   | list of recipe ingredients |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
   
   
   ### Grocery List
 | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user grocery list (default field) |
   | userId        | Pointer to User| grocery list owner |
   | ingredient    | String   | ingredient added by user |
   | quantity      | Number   | amount of ingredients |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
   
### Networking
#### List of network requests by screen
   - Home Feed Screen
      - (Read/GET) Query 10 random recipies.

         ```JAVA
        OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		.url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=10")
		.get()
		.addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
		.addHeader("x-rapidapi-key", "856832bf48msh783ac55978b7517p16fe50jsn043078a575d9")
		.build();

		Response response = client.newCall(request).execute();
         ```
	 
     - (Create/POST) Add recipie to Favorites
     - (Create/POST) Add ingredietns to grocery list.
     - (Delete) Delete recipie from favorites.
  - Favorites Screen
     - (Read/GET) Query all favorites by the user.
     - (Delete) Delete recipie from favorites.
  - Grocery List Screen
     - (Read/GET) Query all ingredients.
     - (Create/POST) Add ingredients to the list.
     - (Delete) Delete ingredients from the list.
     - (Update/PUT) Update amount per ingredient in the list.
#### [OPTIONAL:] Existing API Endpoints
##### A Recipe - Food - Nutrition API
- Base URL - [https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/](https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | recipes/random?number=10 | Get 10 random meal recipies
    `GET`    | /recipes/479101/information | Get information of a specific recipie id
    `GET`    | /recipes/1003464/ingredientWidget.json | Get ingredients list of a specific recipie id
