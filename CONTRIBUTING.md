# Contributor guidelines

## Making changes

All new changes must be made on a separate branch that contains the latest changes from the `development` branch.

#### Creating a new change branch
1. `git checkout development`
2. `git pull origin development`
3. `git checkout -b MODULE_NAME/CHANGE_TYPE/CHANGE_NAME` where:
    - `MODULE_NAME` is the name of the module you are making changes to 
    - `CHANGE_TYPE` is either *feature* or *bugfix*
    - `CHANGE_NAME` is a 2-3 underscore-separated word description of the change you are implementing. 
  
After you're done, push the changes to your fork and create a pull request to the origin `development` branch.

## Code style

In order to keep the code style uniform throughout the project, we've provided you with a style file called `codestyle.xml` located in the project's root directory. 
`codestyle.xml` works with [IntelliJ IDEA](https://www.jetbrains.com/help/idea/configuring-code-style.html) and Visual Studio Code.
