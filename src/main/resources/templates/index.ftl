<!DOCTYPE html>

<html lang="en" ng-app="crudApp">
    <head>
        <title>${title}</title>
        <link href="webjars/bootstrap/3.3.7/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>
        <div ui-view></div>
        <script src="webjars/angularjs/1.6.5/angular.min.js" ></script>
        <script src="webjars/angular-ui-router/1.0.15/release/angular-ui-router.min.js" ></script>
        <script src="webjars/localforage/1.4.1/dist/localforage.min.js" ></script>
        <script src="webjars/ngstorage/0.3.11/ngStorage.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/UserService.js"></script>
        <script src="js/app/UserController.js"></script>
    </body>
</html>