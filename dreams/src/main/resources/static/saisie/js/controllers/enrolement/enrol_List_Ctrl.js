saisie.controller('enrol_List_Ctrl', ['$scope', '$rootScope', '$http', '$filter', '$stateParams','$timeout', function ($scope, $rootScope, $http, $filter, $stateParams,$timeout) {
    console.log("entrer dans enrol_List_Ctrl");
    var dataValuesUrl = serverAdresse+"dataValue/orgPro";
    var instanceUrl = serverAdresse+"instance";
    var DATE_FORMAT = 'dd-MM-yyyy HH:mm:ss';
    var prog = "prog=";
    var org = "org=";
    var listeInstance = [];
    $rootScope.leProgramme = {};
    //$rootScope.elementProgramme = [];
    var nosElements = [];
    var data = [];
    $rootScope.VadInfo = {};
    $rootScope.allData = [];
    var orgSelest = {};
    orgSelest = $stateParams.org;
    $scope.infoSearch = {};


    console.log("orgSelest = ",orgSelest);

    if(orgSelest){
        formateMetaData();
    }else{
        console.log("orgSelest not exist");
        toastr["success"]("Aucune organisation selectionné");
    }

    $rootScope.lancerVADList = function () {
        formateMetaData();
    };

    function formateMetaData() {
        for (var i = 0; i < $rootScope.lesProgrammes.length; i++) {
            if ($rootScope.lesProgrammes[i].code == "enrolement") {
                //$rootScope.VadInfo.programme = $rootScope.programme[i].id;
                $rootScope.programmeSelect = $rootScope.lesProgrammes[i];
                console.log("$rootScope.programmeSelect = ", $rootScope.programmeSelect);
                if($rootScope.programmeSelect.elements.length == 0){
                    toastr["success"]("Aucune Element n'existe");
                    return;
                }
                break;
            }
        }
        if (!$rootScope.orgUnitSelect || !$rootScope.orgUnitSelect.id) {
            $rootScope.getOrgSelect(orgSelest);
        }
        getdata();
    }

    function getdata(page) {
        var org = "org=" + $rootScope.orgUnitSelect.id;
        var prog = "prog=" + $rootScope.programmeSelect.id;
        var ValuesUrl;
        if(page){
            ValuesUrl = dataValuesUrl + "?" + prog + "&" + org+page;
        }else{
            ValuesUrl = dataValuesUrl + "?" + prog + "&" + org;
        }

        $http.get(ValuesUrl).then(function (response) {
            //console.log("getdata() response = ", response);
            data = response.data.content;
            gestPagging(response.data);
            if (data.length > 0) {
                console.log("getdata() data = ", data);
                $rootScope.allData = data;
                getElements();
            }

        }, function (err) {
            console.log(err);
        });
    }

    function gestPagging(data) {
        $scope.infoSearch.totalPages = data.totalPages;
        $scope.infoSearch.totalElements = data.totalElements;
        $scope.infoSearch.taille = data.size;
        $scope.infoSearch.page = data.number;
        $scope.infoSearch.pagging = [];
        var laPage = 0;

        for(var i = 0 ; i < data.totalPages;i++){
            laPage += 1;
            $scope.infoSearch.pagging.push(laPage);
        }
        console.log("getdata() infoSearch = ", $scope.infoSearch);
    }

    function getElements() {

        $rootScope.elementProgramme = [];

        for (var l = 0, k = $rootScope.programmeSelect.elements.length; l < k; l++) {

            if ($rootScope.programmeSelect.elements[l].element.code == "dat_enrol") {
                nosElements.push($rootScope.programmeSelect.elements[l].element);
            }
            if ($rootScope.programmeSelect.elements[l].element.code == "id_dreams") {
                nosElements.push($rootScope.programmeSelect.elements[l].element);
            }
            if ($rootScope.programmeSelect.elements[l].element.code == "nom") {
                nosElements.push($rootScope.programmeSelect.elements[l].element);
            }
            if ($rootScope.programmeSelect.elements[l].element.code == "pren") {
                nosElements.push($rootScope.programmeSelect.elements[l].element);
            }
            if ($rootScope.programmeSelect.elements[l].element.code == "age_enrol") {
                nosElements.push($rootScope.programmeSelect.elements[l].element);
            }
            if (nosElements.length == 5) {
                break;
            }


        }
        console.log("getElements() nosElements = ", nosElements);

        contituerListe();
    }

    function contituerListe() {
        var liste= [];
        for (var i = 0; i < data.length; i++) {
            var temp = {};
            if(data[i].instance){
                temp.instance = data[i].instance;
                for (var k = 0; k < nosElements.length; k++) {
                    for (var j = 0; j < data[i].dataValue.length; j++) {
                        var date1 = new Date(data[i].dataValue[j].dateUpdate);
                        if (temp.update && temp.update < date1) {
                            temp.update = date1;
                        } else {
                            temp.update = date1;
                        }
                        if (nosElements[k].id == data[i].dataValue[j].element) {
                            if(nosElements[k].ensembleOption && angular.isObject(nosElements[k].ensembleOption)){
                                temp[nosElements[k].code] = [];
                                temp[nosElements[k].code] = getOptions(nosElements[k].ensembleOption.options,data[i].dataValue[j].value);
                            }else{
                                temp[nosElements[k].code] = data[i].dataValue[j].value;
                            }
                        }
                    }
                }
                temp.update = $filter('date')(temp.update, DATE_FORMAT);
                liste.push(temp);
            }else{
                $scope.infoSearch.totalElements -= 1;
            }

        }

        console.log("lesEnrol = ", liste);
        $scope.lesEnrol = liste;

    }

    $scope.deleteInstance = function (instance) {
        console.log("instance = " + instance);
        instanceUrl = instanceUrl + "/" + instance;
        $http.delete(instanceUrl).then(function (response) {
            console.log(response);
            getdata();
        }, function (err) {
            console.log(err);
        });
    };

    function getOptions(options, value) {
        //console.log("getOptions() > options",options," // value = ",value);
        var conti = true;
        var debut = 0, point = 0;
        var list = []; var opt = "";
        while(conti){
            point = value.indexOf(" ",debut);
            if(point != -1){
                opt = value.substring(debut, point);
                list.push(getOptionValue(options, opt));
                point++;
            }else{
                opt = value.substring(debut, value.length);
                list.push(getOptionValue(options, opt));
                conti = false;
            }
            debut = point;
        }
        return list;
    }

    function getOptionValue(options, value) {
        //console.log("getOptionValue() > options",options," // value = ",value);
        for(var i = 0;i<options.length;i++){
            if(options[i].code == value){
                return options[i].name;
            }
        }
        return value;
    }

    $scope.affichePage = function (page) {
        console.log("affichePage() > page = ",page);
        if(page != $scope.infoSearch.page){
            var aller = "&page="+page;
            getdata(aller);
        }
    }

}]);