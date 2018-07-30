//var baseUrl = "http://localhost:8080";
var serverAdresse = "";

var conf = angular.module('config', ['ui.router','tree_directive']);
conf.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider) {
    var accueil = {
        name: 'accueil',
        url: '/:user',
        templateUrl: 'views/accueil.html',
        controller: 'accueilCTRL'
    };
    var org = {
        name: 'org',
        url: '/org',
        templateUrl: 'views/organisation.html',
        controller: 'organisationCTRL'
    };
    var user = {
        name: 'user',
        url: '/user',
        templateUrl: 'views/users.html',
        controller: 'usersCTRL'
    };
    var role = {
        name: 'roles',
        url: '/role',
        templateUrl: 'views/rolesUser.html',
        controller: 'rolesUserCTRL'
    };
    var programme = {
        name: 'programme',
        url: '/programme',
        templateUrl: 'views/programme.html',
        controller: 'programmeCTRL'
    };
    var element = {
        name: 'element',
        url: '/element',
        templateUrl: 'views/element.html',
        controller: 'elementCTRL'
    };

    var ensembleOption = {
        name: 'ensembleOption',
        url: '/ensembleOption',
        templateUrl: 'views/ensembleOption.html',
        controller: 'ensembleOptionCTRL'
    };
    var service = {
        name: 'service',
        url: '/service',
        templateUrl: 'views/service.html',
        controller: 'serviceCTRL'
    };
    var imports = {
        name: 'imports',
        url: '/imports',
        templateUrl: 'views/imports.html',
        controller: 'importCTRL'
    };
    
    $stateProvider.state(accueil);
    $stateProvider.state(org);
    $stateProvider.state(user);
    $stateProvider.state(role);
    $stateProvider.state(programme);
    $stateProvider.state(element);
    $stateProvider.state(ensembleOption);
    $stateProvider.state(service);
    $stateProvider.state(imports);

}]);
conf.run(['$rootScope','$http', function ($rootScope,$http) {
    console.log("entrer dans run");
    var hre = location.href;
    serverAdresse = hre.substring(0,hre.indexOf("config/index.html"));
    console.log("entrer dans serverAdresse = ",serverAdresse);
    $rootScope.baseUrl = serverAdresse;
    var meUrl = serverAdresse+"me";
    var exit = serverAdresse+"login?logout";
    var Me = {};
    $rootScope.Me = {};


    getMe();

    function getMe() {
        $http.get(meUrl).then(function (response) {
            console.log("getMe() > response = ",response);
            if (response) {
                Me = response.data;
                $rootScope.Me = Me;
                gestRole();
            }
        }, function (err) {
            console.log(err);
        });
    };
    function gestRole() {
        $rootScope.role = {};

        for(var i = 0;i<Me.roleDefinis.length;i++){
            $rootScope.role[Me.roleDefinis[i]] = true;
        }
    }
    $rootScope.logout = function () {
        window.location = exit;
    }
}]);