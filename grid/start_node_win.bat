cd %~dp0

set CHROME_DRIVER=-Dwebdriver.chrome.driver="%~dp0\win\chromedriver.exe"
set FIREFOX_DRIVER=-Dwebdriver.gecko.driver="%~dp0\win\geckodriver.exe"

set DRIVERS=%CHROME_DRIVER% %FIREFOX_DRIVER%

java %DRIVERS% -jar selenium-server-standalone-3.141.59.jar -role node -nodeConfig node_config_win.json