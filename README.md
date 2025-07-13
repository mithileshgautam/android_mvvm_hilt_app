Sample app that demonstrates the usage of Dagger Hilt with Kotlin & MVVM architecture by loading product list from FakeStore API to show one approach to using some of the best practices in Android Development.
Including:

ViewModel
Flow
Hilt (for dependency injection)
Kotlin Coroutines
Retrofit
Room
Offline sync

User Stories
1. As a user, I want to see a list of products
• On opening the app, a list of products from the API should be displayed.
• Each item should show at least the product title, price, category and thumbnail
image.
2. As a user, I want to see previously loaded data when offline
• If there’s no internet connection, I should still be able to view the last synced
products from local storage.
3. As a user, I want to know when I'm viewing offline data
• If the device is offline and cached data is shown, display a small
banner/toast/snackbar.

