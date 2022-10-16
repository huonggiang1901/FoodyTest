
set projectLocation=%~dp0
set reportPath=%projectLocation%allure-results
set allurePath="%projectLocation%src\test\resources\allure\2.13.8\bin"

call %allurePath%\allure serve %reportPath%

PAUSE

