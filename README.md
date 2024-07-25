This is basic application which leverages the open source unsplash api. It displays list of restaurants. It has following screens:
- Home Screen is used to display all searched restaurants.
- Search Screen is used to display top restaurants as per user input city with paginated response (page size = 20). It makes use of Paging Remote Mediator to get the data and store it in local storage.
- Detail Screen is used to show full details of the restaurants such as image, name, reviews, etc. User can also "thumbs down" an restaurant here and thus filtering this specific restaurant from all list of restaurants.
- Login Screen is used for user login flow.
- Register Screen is used for user registration.

This application is developed using Jetpack Componse.

This application leverages following implementations :-

- Navigations - The Navigation library of Jetpack suite to handle navigations.
- Retrofit - To make api calls
- HttpClient - This is used as network client for retrofit
- Interceptor - This is used to make custom requests before each api call
- Kotlinx Serialization - This is used for serialization purposes to convert api response in json data and later inserted into Room db.
- Room - It is used for offline support
- Paging 3 - The paging 3 library of jetpack suite is used to handle paging calls.
- Hilt - This library is used for dependency injection
- Coil - This library is used to load images from internet into the composables
- ViewModel - This library is used for state management across configuration changes
- Coroutines - It is used to handle asynchronous task operations such as api calls.
