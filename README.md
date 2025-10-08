# CatApp
The CatApp is an Android mobile application using Kotlin and traditional Android XML layouts. The app follows the MVC (Model-View-Controller) architecture. This application consumes The [Cat API](https://thecatapi.com/) to display cats.
<video src="https://github.com/user-attachments/assets/61f006b9-5144-404b-942a-9843dedb6c79" controls></video>

# ✨ Features
This application consists of multiple activities and fragments following the MVC architecture. It also implements four important and commonly used XML layouts, listed below:

- RecyclerView: Efficiently displays a list of cats and their descriptions through view recycling.

- Navigation Drawer (DrawerLayout): Implements a side-panel navigation menu that slides in from the left edge.

- ViewPager: Opens when the Info option from the navigation drawer is selected, allowing horizontal swiping between multiple screens or fragments.

- Bottom Navigation (BottomNavigationView): Appears when a card in the RecyclerView is selected, enabling navigation between sections within the new activity.

# 📚 Libraries Used

- Retrofit: For making HTTP requests to the remote API.

- OkHttp: As the underlying HTTP client for handling network calls efficiently.

- Gson: For parsing and converting JSON data to and from Kotlin objects.

# ⚙️ Architecture
- Model: Holds the data models and logic.
- View: Responsible for the UI elements and display of data (XML layouts and Activities/Fragments).
- Controller: Handles user input, manages View updates, and interacts with the Model.


