# Naming convention

## 1. Directory Naming

* Directory names shall follow camelCase (first letter of first word is lower case, and first letter of each word after that must be uppercase).
* The directory names shall be short and meaningful.

`Example: data, screenshots, dataObject, ...`

## 2. Java naming conventions

###a. Class names

* Class names should be simple full English descriptor noun.
* Use PascalCase(uppercase first letter of each word).
* Whole words should be used instead of acronyms and abbreviations unless the abbreviation is more widely used than the long-form.

`Example: GeneralPage, LoginPage, TestLogin, ...`

###b. Method names

* Lower camel case should be used for the method names.
* Method name should be a verb.
* Try to make the name simple and descriptive.
* Use whole word instead of abbreviations.

```
Example:
    waitForLoginPageOpened()
    testLoginWithValidAccount() : All test methods shall start with prefix test
```

###c. Variable names

* Use camelCase (first letter of first word is lower case, and first letter of each word after that must be uppercase).
* Intent of the variable shall be indicated with the given name.
* The variable name should be a noun.
* Try to make the name simple and descriptive.

`Example: webDriver, loginPage, ...`

* Use following prefix for the web elements to indicate the type of the element:

|Prefix|Example|Description|
|---|---|---|
|btn|btnLogin|Button|
|ckb|ckbStatus|Checkbox|
|lbl|lblUser|Label|
|txt|txtPassword|Text box|
|rdb|rdbOption|Radio button|

###d. Constants

* All letters should be in uppercase.
* Words shall be separated with underscore _ .

`Example: FINAL_VALUE, ...`

##3. Comments

* Comments shall be used to explain the code to make the test scripts more readable for others.
* Use consistent commenting styles.

###a. Single line comments

```
// This is comment
```

###b. Multiple line

```
/*  
    This   
    is   
    multi line   
    comment  
*/ 
```

###c. Documentation Comments

* All methods must be documented.
* Each method should comment exactly in the sequence as given below.
* Each line item begins with an asterisk.
* All subsequent lines in multiline component are to be indented so that they line up vertically with the previous line.

```
/** 
* This method calculates the summation of two integers
* @param input1 This is the first parameter to sum() method 
* @param input2 This is the second parameter to the sum() method
* @return int This returns the addition of input1 and input2 
*/  
public int sum(int input1, int input2){  
    return input1 + input2;  
}
```

##4. Test case

###a. Logger for steps and verify points in test case

* Use Logger.info() to log steps in test case.
* Use Logger.verify() to log verìy points in test case.

```
Logger.step("1. Navigate to google.com");
Logger.verify("google.com page is opened");
```

###b. Assert in test case

* Should use methods in class AssertHelper and have expectation fail output for each assert.

```
AssertHelper.checkEqual(actualMsg, expectedMsg, "Message is not displayed as expected");
```

Refer: http://pragmatictestlabs.com/2018/03/05/coding-convention-selenium-java/
