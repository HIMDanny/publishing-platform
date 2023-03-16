### Чернетка, лише для базового ознайомлення

### **Authentication:** (8090) <br />

POST: */api/v1/auth/register* (Request body: firstName, lastName, email, password) - реєстрація. Повертає access токен. <br />
POST: */api/v1/auth/authenticate* (Request body:  email, password) - автентифікація (логін).  Повертає access токен.





### **User:** (8080) <br />

**GET**: */api/v1/users* - повертає усіх користувачів. <br />
**GET**: */api/v1/users/{id}* - повертає користувача за ід. <br />
**GET**: */api/ v1/users?email=...* - повертає користувача за email. <br /> 
**POST**: */api/v1/users* (Request body: firstName, lastName, email, password) - чисте збереження користувача в базі даних. Повертає id користувача. <br />
**PUT**: */api/v1/users/{id}* (Request body: firstName, lastName, email, password (???)) - оновлення користувача по id. Повертає оновленого користувача. <br />
**DELETE**: */api/v1/users/{id}* - видалення користувача. Повертає true/false.  <br />
**GET**: */api/v1/users?field=...&direction=...* - сортування усіх користувачів. <br />
**GET**: */api/v1/users/{id}/articles?page=...&pageSize=...&field=...&direction=...* - повернення статтей користувача по його id. Усі квері параметри, крім id, не є обов'язковими. <br />
**GET**: */api/v1/users/pagination?page=...&pageSize=...&field=...&direction=...* - повернення користувачів з пагінацією та сортуванням. Усі квері параметри не є обов'язковими. <br />
GET: */api/v1/users/search/all?value=...&field=...&direction=...* - пошук користувачів по ключовому слову з можливістю сортування. Пошук здійснюється лише по first name та last name. Параметри field та direction не є обов'язковими, на відміну від value. <br />
GET: */api/v1/users/search?value=...&page=...&pageSize=...&field=...&direction=...* - пошук користувачів по ключовому слову з пагінацією та сортуванням. Пошук здійснюється лише по first name та last name. Параметри пагінації та сортування не є обов'язковими, на відміну від value. <br />

Якщо параметри пагінації не вказані, по-замовчуванню застосовуються наступні: <br />
**page** = 1 <br />
**pageSize** = 10 <br />
**field** = id <br />
**direction** = asc <br />

API, що повертає користувача, повертає наступні поля: <br />
**id** <br />
**firstName** <br />
**lastName** <br />
**email** <br />
**role** <br />
**page** - сторінка статтей користувача. Ця сторінка містить: totalElements, totalPages, articles, page, pageSize. <br />

API з пагінацією повертає: <br />
**totalElements** - загальна кількість знайдених користувачів. <br />
**totalPages** - кількість знайдених сторінок з користувачами. <br />
**page** - номер сторінки <br />
**pageSize** - кількість елементів на сторінці <br />
**users** - список користувачів <br />

Для API    _/users_,    _/users/{id}_,    _/users?email_,    _/users?field&direction_,      _/users/pagination_,     
_/users/search_              повертаються 10 статтей, що мають найбільше переглядів 
(буде змінено на "актуальні" статті - сортування за інтенсивістю переглядів). Також це відноситься до оновлення 
користувача. <br />





### **User:** (8086) <br />
**GET**: _/api/v1/articles_ - повертає усі статті. <br />
**GET**: _/api/v1/articles/{id}_ - повертає статтю за ід. <br />
**POST**: _/api/v1/articles_ (Request body: title, content, mainImagePath, authorId, categoryId) - збереження статті в базі даних. Повертає id статті. <br />
**PUT**: _/api/v1/articles/{id}_ (Request body: title, content, mainImagePath, authorId, categoryId (???)) - оновлення статті по id. Повертає оновлену статтю. <br />
**DELETE**: _/api/v1/articles/{id}_ - видалення статті. Повертає true/false. <br />
**GET**: _/api/v1/articles?field=...&direction=..._ - сортування усіх статтей. Параметри не обов'язкові. <br />
**GET**: _/api/v1/articles/pagination?page=...&pageSize=...&field=...&direction=..._ - повернення статтей з пагінацією та сортуванням. Усі квері параметри не є обов'язковими. <br />
**GET**: _/api/v1/articles/search/all?value=...&field=...&direction=..._ - пошук статтей по ключовому слову з можливістю сортування. Пошук здійснюється по назві та вмісту статті. Параметри field та direction не є обов'язковими, на відміну від value. <br />
**GET**: _/api/v1/articles/search?value=...&page=...&pageSize=...&field=...&direction=..._ - пошук статтей по ключовому слову з пагінацією та сортуванням. Пошук здійснюється по назві та вмісту статті. Параметри пагінації та сортування не є обов'язковими, на відміну від value. <br />

Якщо параметри пагінації не вказані, по-замовчуванню застосовуються наступні: <br />
**page** = 1 <br />
**pageSize** = 10 <br />
**field** = id <br />
**direction** = asc <br />

API, що повертає статтю, повертає наступні поля: <br />
**id** <br />
**title** <br />
**content** <br />
**mainImagePath** <br />
**publishingDate** <br />
**minutesToRead** <br />
**numberOfViews** <br />
**numberOfLikes** <br />
**author** <br />
**category** <br />

API з пагінацією повертає: <br />
**totalElements** - загальна кількість знайдених статей. <br />
**totalPages** - кількість знайдених сторінок зі статтями. <br />
**page** - номер сторінки <br />
**pageSize** - кількість елементів на сторінці <br />
**article** - список статтей <br />




### **Category:** (8087) <br />
**GET**: _/api/v1/categories_ - повертає усі категорії. <br />
**GET**: _/api/v1/categories/{id}_ - повертає категорію за ід. <br />
**POST**: _/api/v1/categories_ (Request body: name) - збереження категорії в базі даних. Повертає id категорії. <br />
**PUT**: _/api/v1/categories/{id}_ (Request body: name) - оновлення категорії по id. Повертає оновлену категорію. <br />
**DELETE**: _/api/v1/categories/{id}_ - видалення категорії. Повертає true/false. <br />
**GET**: _/api/v1/categories?field=...&direction=..._ - сортування усіх категорій. Параметри не обов'язкові. <br />
**GET**: _/api/v1/categories/{id}/articles?page=...&pageSize=...&field=...&direction=..._ - повернення статтей категорії по її id. Усі квері параметри, крім id, не є обов'язковими. <br />
**GET**: _/api/v1/categories/pagination?page=...&pageSize=...&field=...&direction=..._ - повернення категорій з пагінацією та сортуванням. Усі квері параметри не є обов'язковими. <br />
**GET**: _/api/v1/categories/search/all?value=...&field=...&direction=..._ - пошук категорій по ключовому слову з можливістю сортування. Пошук здійснюється по назві категорії. Параметри field та direction не є обов'язковими, на відміну від value. <br />
**GET**: _/api/v1/categories/search/all?value=...&page=...&pageSize=...&field=...&direction=..._ - пошук категорій по ключовому слову з пагінацією та сортуванням. Пошук здійснюється по назві категорії. Параметри пагінації та сортування не є обов'язковими, на відміну від value. <br />

Якщо параметри пагінації не вказані, по-замовчуванню застосовуються наступні: <br />
**page** = 1 <br />
**pageSize** = 10 <br />
**field** = id <br />
**direction** = asc <br />

API, що повертає категорію, повертає наступні поля: <br />
**id** <br />
**name** <br />
**page** - сторінка статтей категорії. Ця сторінка містить: totalElements, totalPages, articles, page, pageSize. <br />

API з пагінацією повертає: <br />
**totalElements** - загальна кількість знайдених категорій. <br />
**totalPages** - кількість знайдених сторінок з категоріями. <br />
**page** - номер сторінки <br />
**pageSize** - кількість елементів на сторінці <br />
**categories** - список категорій <br />

Для API    _/categories_,    _/categories/{id}_,          _/categories?field&direction_,      _/categories/pagination_,         _/categories/search_              повертаються 10 статтей, що мають найбільше переглядів (буде змінено на "актуальні" статті - сортування за інтенсивістю переглядів). Також це відноситься до оновлення категорії.