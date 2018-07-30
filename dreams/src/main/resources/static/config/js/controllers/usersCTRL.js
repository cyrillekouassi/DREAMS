conf.controller('usersCTRL',['$scope','$http','$window',function ($scope,$http,$window) {
    console.log("entrer dans users");
    var allUsers = [];
    var allOrgUnit = [];
    var allRolesUser = [];
    $scope.orgSelect = [];
    $scope.RolesSelect = [];
    var meDataView = [];
    var leParent = {};
    $scope.utilisateur = [];
    $scope.rolesUser = [];
    $scope.afficheAjout = true;
    $scope.afficheSaisi = false;
    $scope.user = {};

    $scope.information = null;


    var urlUser = serverAdresse+"user";
    var organisationUrl = serverAdresse+"organisation";
    var roleUserUrl = serverAdresse+"roleUser";


    lancerAllOrg();

    function lancerAllRolesUser() {
        $http.get(roleUserUrl).then(function (response) {
            console.log("roleUserUrl ==>",response);
            if (response) {
                allRolesUser = response.data;
                $scope.rolesUser = angular.copy(allRolesUser);
                //$scope.utilisateur = allUsers;
            }
        }, function (err) {
            console.log(err);
        });
    }
    function lancerAllUser() {
        $http.get(urlUser).then(function (response) {
            console.log("User ==>",response);
            if (response) {
                allUsers = response.data;
                $scope.utilisateur = allUsers;
                lancerAllRolesUser();
            }
        }, function (err) {
            console.log(err);
        });
    }
    function lancerAllOrg() {
        $http.get(organisationUrl).then(function (response) {
            console.log("organisationUrl ==>",response);
            if (response) {
                allOrgUnit = angular.copy(response.data);
                MeOrgName();
                lancerAllUser();
            }
        }, function (err) {
            console.log(err);
        });
    }
    function MeOrgName() {
        //console.log("MeOrgName");
        orgEntete();
        var a = 0, b = meDataView.length;
        while (a < b) {
            for (var i = 0, j = allOrgUnit.length; i < j; i++) {
                if (meDataView[a].id === allOrgUnit[i].id) {
                    meDataView[a] = allOrgUnit[i];
                    meDataView[a].children = allOrgUnit[i].childrens;
                    delete meDataView[a].childrens;
                    if (meDataView[a].children.length > 0) {
                        meDataView[a].collapse = true;
                        meDataView[a].children = getChildren(meDataView[a].children);
                    }
                    break;
                }
            }
            a++;
        }
        console.log("meDataView ==>");
        console.log(meDataView);
        $scope.arbre = meDataView;
    }
    function orgEntete() {

        meDataView = [];
        for (var i = 0, j = allOrgUnit.length; i < j; i++) {
            if (allOrgUnit[i].level === 1) {
                var tp = {};
                tp.id = allOrgUnit[i].id;
                meDataView.push(tp);
                console.log("MeOrgName() tp = ",tp);
                //break;
            }
        }
        console.log("MeOrgName() meDataView = ",meDataView);
    }

    function getChildren(child) {
        console.log("getChildren");
        var a = 0, b = child.length;
        while (a < b) {
            for (var i = 0, j = allOrgUnit.length; i < j; i++) {
                if (child[a].id === allOrgUnit[i].id) {
                    child[a] = allOrgUnit[i];
                    child[a].children = allOrgUnit[i].childrens;
                    delete child[a].childrens;
                    if (child[a].children.length > 0) {
                        child[a].children = getChildren(child[a].children);
                    }
                    break;
                }
            }
            a++;
        }
        return child;
    }

    $scope.AllUsers = function () {
        lancer();
    };

    $scope.newUser = function () {
        $scope.afficheSaisi = true;
        $scope.afficheAjout = true;
    };
    $scope.annuler = function () {
        $scope.user = {};
        $scope.orgSelect = [];
        $scope.RolesSelect = [];
        $scope.afficheSaisi = false;
        $scope.afficheAjout = true;
    };

    $scope.organisationSelected = function (org) {
        //console.log("organisationSelected = ",org);
        $scope.orgSelect = [];
        for(var i = 0;i<org.length;i++){
            var tmp = {};
            tmp.name = org[i].name;
            tmp.id = org[i].id;
            $scope.orgSelect.push(tmp);
        }
    };
    
    $scope.ajouterUser = function () {
        console.log("ajouterUser() debut > user = ", $scope.user);
        $scope.information = null;
        if(!$scope.user.password1 || !$scope.user.password2){
            $scope.information = "Mot de passe obligatoire";
            return;
        }
        if($scope.user.password1 === $scope.user.password2){
            $scope.user.password = $scope.user.password1;
            delete $scope.user.password1;
            delete $scope.user.password2;
        }else{
            $scope.information = "Mot de passe et sa repétition ne sont pas identique";
            return;
        }
        if(!$scope.user.username){
            $scope.information = "Nom d'utilisateur obligatoire";
            return;
        }if(!$scope.user.name){
            $scope.information = "nom obligatoire";
            return;
        }
        if((!$scope.user.password1 && $scope.user.password2) || ($scope.user.password1 && !$scope.user.password2)){
            $scope.information = "Mot de passe est obligatoire et doit être repété";
            return;
        }
        $scope.user.organisations = [];
        $scope.user.organisations = getOrgSelected();
        $scope.user.roleUsers = [];
        $scope.user.roleUsers = getRolesSelected();
        console.log("ajouterUser() fin > user = ", $scope.user);
        saveUser($scope.user);
    };

    function getOrgSelected() {
        var org = [];
        for(var i=0;i<$scope.orgSelect.length;i++){
            var tmp = {};
            tmp.id = $scope.orgSelect[i].id;
            org.push(tmp);
        }
        return org;
    }

    function saveUser(user) {
        var url = serverAdresse+'user';
        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(url, user, config).then(function(succes){
            console.log("saveUser () succes = ",succes);
            if(angular.isObject(succes.data)){
               console.log("saveUser () echec");
                sortir();
            }else{
                $scope.annuler();
            }

        }, function(error){
            console.log("saveUser() error = ",error);
            //sortir();
        });
    }

    function sortir() {
        $scope.afficheSaisi = false;
        $scope.user = {};
        $window.location.reload();
    }

    $scope.deleteOrg = function (index) {
        $scope.orgSelect.splice(index,1);
    };

    $scope.modifierUser = function (use) {
        console.log("modifierUser() user = ",use);
        $scope.user = use;
        $scope.afficheSaisi = true;
        $scope.afficheAjout = false;
        $scope.orgSelect = [];
        $scope.RolesSelect = [];
        $scope.RolesSelect = $scope.user.roleUsers;
        $scope.rolesUser = angular.copy(allRolesUser);
        for(var i = 0;i< $scope.user.organisations.length;i++){
            for(var j=0;j<allOrgUnit.length;j++){
                if($scope.user.organisations[i].id == allOrgUnit[j].id){
                    var tmp = {};
                    tmp.id = allOrgUnit[j].id;
                    tmp.name = allOrgUnit[j].name;
                    $scope.orgSelect.push(tmp);
                    break;
                }
            }
        }
        for(var i = 0;i< $scope.RolesSelect.length;i++){
            for(var j=0;j<$scope.rolesUser.length;j++){
                if($scope.RolesSelect[i].id == $scope.rolesUser[j].id){
                    var tp = $scope.rolesUser[j].id;
                    console.log("tp = ",tp);
                    $scope.rolesUser[j][tp] = true;
                    var tmp = {};
                    tmp.id = $scope.rolesUser[j].id;
                    tmp.name = $scope.rolesUser[j].name;
                    $scope.RolesSelect[i] = tmp;
                    break;
                }
            }
        }
        console.log("modifierUser() $scope.rolesUser = ",$scope.rolesUser)
        console.log("modifierUser() $scope.rolesUser = ",$scope.rolesUser)
    };

    $scope.rolesUsed = function (role, value) {
        console.log("rolesUsed() role = ",role," // value = ", value);
        if(value){
            $scope.RolesSelect.push(role);
        }
        if(!value){
            for(var i = 0;i<$scope.RolesSelect.length;i++){
                if($scope.RolesSelect[i].id == role.id){
                    $scope.RolesSelect.splice(i,1);
                }
            }

        }
    };

    function getRolesSelected() {
        var role = [];
        for(var i=0;i<$scope.RolesSelect.length;i++){
            var tmp = {};
            tmp.id = $scope.RolesSelect[i].id;
            role.push(tmp);
        }
        return role;
    }

    $scope.appliqModif = function () {
        $scope.information = null;
        console.log("appliqModif() debut > user = ", $scope.user);
        if($scope.user.password1 && $scope.user.password2 ){
            if($scope.user.password1 === $scope.user.password2){
                $scope.user.password = $scope.user.password1;
                delete $scope.user.password1;
                delete $scope.user.password2;
            }else{
                $scope.information = "Mot de passe et sa repétition ne sont pas identique";
                return;
            }
        }
        if((!$scope.user.password1 && $scope.user.password2) || ($scope.user.password1 && !$scope.user.password2)){
            $scope.information = "Mot de passe est obligatoire et doit être repété";
            return;
        }
        if(!$scope.user.username){
            $scope.information = "Nom d'utilisateur obligatoire";
            return;
        }if(!$scope.user.name){
            $scope.information = "nom obligatoire";
            return;
        }
        $scope.user.organisations = [];
        $scope.user.organisations = getOrgSelected();
        $scope.user.roleUsers = [];
        $scope.user.roleUsers = getRolesSelected();
        console.log("appliqModif() fin > user = ", $scope.user);
        updateUser($scope.user.id,$scope.user)
    }

    function updateUser(id,user) {
        var url = serverAdresse+'user/'+id;
        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.put(url, user, config).then(function(succes){
            console.log("updateUser() succes = ",succes);
            sortir();
        }, function(error){
            sortir();
        });
    }

}]);