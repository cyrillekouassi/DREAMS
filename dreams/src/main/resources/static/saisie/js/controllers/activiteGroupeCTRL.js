saisie.controller('activiteGroupeCTRL',['$scope','$rootScope','$http','$stateParams','$state','$timeout',function ($scope,$rootScope,$http,$stateParams,$state,$timeout) {
    //console.log("entrer dans activiteGroupeCTRL");
    var dataInstanceValue = serverAdresse+"dataValue/";
    var dataValueUrl = serverAdresse+'dataValue';
    var searchBeneficiaire = serverAdresse+"beneficiaire?";
    $scope.vadData = {};
    $scope.groupeSession = {};
    $scope.groupe = {};
    var modifSession = {};
    var dataInstance = {};
    var elementCode = [];
    var instanceValue = [];
    var instanceValueEnrolement = [];
    var dataInstanceEntete = {};
    var autocompleteData = [];
    var materielQte = {};
    var tempData=[];
    dataInstanceEntete.programme = $stateParams.prog;
    dataInstanceEntete.organisation = $stateParams.org;
    dataInstanceEntete.instance = $stateParams.inst;
    dataInstance = dataInstanceEntete;


    var groupeTable = ["nomOrganisation","nomSafespace","nomAgentPrinc","prenomAgentPrinc","contactAgentPrinc","nomAgentSecond","prenomAgentSecond","contactAgentSecond","region","district","sousPrefect","commune","quartier","infoSession","thematique","trancheAge"];
    var sessionTable = ["dateSession","themeAborde","methodologie","outils","materielQuantite"];
    var benefTable = ["id_dreams","nomPrenomBenef","contactBenef","benefSession","benefAyantTernime","totalBenefSession","totalBenefAyantTernime"];
    var autreCibleTable = ["codeBenef","nomPrenomAutreCible","contactAutreCible","sexeAutreCible","autreCibleSession","autreCibleAyantTernimeMasc","autreCibleAyantTernimeFem","totalAutreCibleSession","totalAutreCibleAyantTernimeMasc","totalAutreCibleAyantTernimeFem"];
    var groupeElement = [];
    var sessionElement = [];
    var benefElement = [];
    var autreCibleElement = [];
    $scope.sessionData = [];
    $scope.ListBenef = [];
    $scope.TotalBenef = {};
    $scope.ListAutreCible = [];
    var benefValue = {};
    var autreCibleValue = {};
    var origin = "";
    initOrg();




    $scope.thematique = {
        model: 'myString',
        availableOptions: [
            {value: 'educationSexuel', name: 'Education sexuelle complète'},
            {value: 'educationFinanciere', name: 'Education financière / Business plus'},
            {value: "sasa", name: 'Sasa'},
            {value: "sinovoyu", name: 'SINOVOYU'},
            {value: "psychoSocial", name: 'Psycho-social'},
            {value: 'avec', name: 'AVEC'}
        ]
    };
    $scope.trancheAge = {
        model: 'myString',
        availableOptions: [
            {value: '10-14', name: '10-14 ans'},
            {value: '15-19', name: '15-19 ans'}
        ]
    };


    gestElements();
    
    function initOrg() {
        $scope.groupe.nomOrganisation = $rootScope.orgUnitSelect.organisationLocal;
        $scope.groupe.nomSafespace = $rootScope.orgUnitSelect.name;
        $scope.groupe.region = $rootScope.orgUnitSelect.region;
        $scope.groupe.sousPrefect = $rootScope.orgUnitSelect.sousPrefecture;
        $scope.groupe.commune = $rootScope.orgUnitSelect.commune;
        //$scope.groupe.district = $rootScope.orgUnitSelect.commune;
        $scope.groupe.quartier = $rootScope.orgUnitSelect.quartier;
    }

    if(dataInstanceEntete.instance){
        if($rootScope.allData){
            for(var i=0;i<$rootScope.allData.length;i++){
                if($rootScope.allData[i].instance == dataInstanceEntete.instance){
                    instanceValue = $rootScope.allData[i].dataValue;
                    console.log("instanceValue = ",angular.copy(instanceValue));
                    mappigData();
                }
            }
        }else{
            origin = "init";
            getdataInstace(dataInstanceEntete.instance);
        }


    }
    function getdataInstace(instance){
        console.log("getdataInstace()");
         var InstanceValue = dataInstanceValue + instance;
        $http.get(InstanceValue).then(function (response) {
            console.log("getdataInstace() response",angular.copy(response));
            if (response) {
                instanceValue = response.data.dataValue;
                getRoute();
            }
        }, function (err) {
            console.log("getdataInstace() err",err);
        });
    }
    function getRoute() {
        if(origin == "init"){
            mappigData();
        }else if(origin == "session"){
            collecteSessionData();
        }else if(origin == "benef"){
            collecteBenefData();
        }else if(origin == "autreCible"){
            collecteAutreCibleData();
        }else if(origin == "idbenef"){
            gestComplete();
        }
    }

    function mappigData() {
        console.log("mappigData");
        collecteGroupeData();
        collecteSessionData();
        collecteBenefData();
        collecteAutreCibleData();
        initOrg();
    }

    $scope.saveMaterial = function () {
        //console.log("saveMaterial()");
        var options = [];
        options = getOption(sessionElement,"materielQuantite");
        $scope.groupeSession.materielQuantite = "";
        $scope.MqteInfo = "";
        for (var prop in $scope.materiel) {
            if($scope.materiel[prop] && materielQte[prop] != null){
                if($scope.groupeSession.materielQuantite == ""){
                    $scope.groupeSession.materielQuantite = materielQte[prop] +" "+prop;
                    $scope.MqteInfo = materielQte[prop] +" "+getOptionValue(options,prop);
                }

                else{
                    $scope.groupeSession.materielQuantite = $scope.groupeSession.materielQuantite +" "+ materielQte[prop] +" "+prop;
                    $scope.MqteInfo = $scope.MqteInfo+"; "+ materielQte[prop] +" "+getOptionValue(options,prop);
                }

            }
        }
    };

    $scope.saveQuantite = function (materiel, quantite) {
        materielQte[materiel] = quantite;
    };

    $scope.saveSession = function () {
        console.log("saveSession()");
        tempData=[];
        origin = "session";
        console.log("saveSession() groupe = ",$scope.groupe);
        console.log("saveSession() groupeSession = ",$scope.groupeSession);
        if(checkgroupeSession()){
            dataInstance = dataInstanceEntete;
            dataInstance.dateActivite = $scope.groupeSession.dateSession;
            dataInstance.dataValue = [];
            if(!dataInstanceEntete.instance){
                delete dataInstance.instance;
                tempData.push($scope.groupe);
                tempData.push($scope.groupeSession);
                //dataInstance.dataValue = [];
                machting();
            }else{
                elementCode = Object.keys($scope.groupeSession);
                if(angular.isObject(modifSession) && !isEmpty(modifSession)){
                    isValeurModifier();
                }else{
                    ajoutSession();
                }
            }
        }
    };

    function checkgroupeSession() {
        console.log("checkgroupeSession()");
        if(!$scope.groupeSession.dateSession)
            return false;
        if(!$scope.groupeSession.themeAborde)
            return false;
        if(!$scope.groupeSession.methodologie)
            return false;
        if(!$scope.groupeSession.materielQuantite)
            $scope.groupeSession.materielQuantite = null;
        if(!$scope.groupeSession.outils)
            $scope.groupeSession.outils = null;
        console.log("checkgroupeSession() > $scope.groupeSession = ",$scope.groupeSession);
        return true;
    }

    function isValeurModifier() {
        console.log("isValeurModifier() elementCode = ",elementCode);
        for(var i =0;i<elementCode.length;i++){
                for(var k=0;k<sessionElement.length;k++){
                    if(elementCode[i] == sessionElement[k].code){
                        var data = {};
                        data.numero = modifSession.numero;
                        data.value = $scope.groupeSession[elementCode[i]];
                        data.element = sessionElement[k].id;
                        dataInstance.dataValue.push(data);
                        break;
                    }
                }
        }
        if(dataInstance.dataValue.length != 0){
            console.log("isValeurModifier() dataInstance.dataValue = ",dataInstance);
            saveData();
        }
        iniSession();
    }

    function ajoutSession() {
        console.log("ajoutSession() elementCode = ",elementCode," // sessionElement = ",sessionElement);
        for(var i = 0;i<elementCode.length;i++){
            for(var k=0;k<sessionElement.length;k++){
                if(elementCode[i] == sessionElement[k].code){
                    var data = {};
                    data.element =  sessionElement[k].id;
                    data.value = $scope.groupeSession[elementCode[i]];
                    dataInstance.dataValue.push(data);
                    break;
                }
            }
        }
        if(dataInstance.dataValue.length != 0){
            //console.log("ajoutSession() dataValue exite");
            console.log("ajoutSession() dataInstance.dataValue = ",dataInstance.dataValue);
            saveData();
        }
        iniSession();
    }

    function iniSession() {
        modifSession = {};
        $scope.groupeSession = {};
        $scope.method = {};
        $scope.outil = {};
        for(var pop in $scope.materiel){
            $scope[pop] = null;
        }
        $scope.materiel = {};
        $scope.methodInfo = null;
        $scope.outilsInfo = null;
        $scope.MqteInfo = null;
    }

    function machting() {
        var element = {};
        for(var i = 0;i<tempData.length;i++){
            var tmp = {};
            tmp = tempData[i];
            for (var prop in tmp) {
                element[prop] = tmp[prop];
            }
        }
        //console.log("machting() element = ",element);
        elementCode = Object.keys(element);
        //console.log("elementCode = ",elementCode);
        getElementid(element)
    }

    function getElementid(valeur) {
        var data = {};
        for(var i=0;i<elementCode.length;i++){
            for(var j = 0;j<$rootScope.programmeSelect.elements.length;j++){

                    if($rootScope.programmeSelect.elements[j].element.code == elementCode[i]){
                        var data = {};
                        data.element = $rootScope.programmeSelect.elements[j].element.id;
                        data.value = valeur[elementCode[i]];
                        dataInstance.dataValue.push(data);
                    }

            }
        }
        console.log("dataInstance = ",dataInstance);
        if(dataInstance.dataValue.length != 0){
            saveData();
        }

        iniSession();
    }

    function saveData() {

        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(dataValueUrl, dataInstance, config).then(function(succes){
            console.log("succes = ",succes);
            //dataInstance = succes.data;
            if(succes.data.status == "ok"){
                dataInstance.instance = succes.data.id;
                dataInstanceEntete.instance = succes.data.id;
                $stateParams.inst = succes.data.id;
                getdataInstace(dataInstance.instance);
            }
        }, function(error){
            //console.log("error = ",error);
            //echecSave();
        });
    }

    $scope.saveValue = function (element,value) {
        console.log("saveValue() element =",element,"//value =",value);

        if(dataInstanceEntete.instance){
            origin = "autre";
            var valueTDO = {};
            valueTDO.element = getElementId(element);
            //valueTDO.instance = dataInstanceEntete.instance;
            valueTDO.numero = $scope.groupe.numero;
            valueTDO.value = value;
            dataInstance = dataInstanceEntete;
            dataInstance.dataValue = [];
            dataInstance.dataValue.push(valueTDO);
            console.log("saveValue() valueTDO = ",valueTDO);
            saveData();
            //getdataInstace(dataInstanceEntete.instance);
            //sendValueTDO(valueTDO);
        }
    };

    $scope.searchId = function (valeur) {
        //console.log("searchId element = ",element," // valeur = ",valeur," //$rootScope.programmeEnrolement = ",$rootScope.programmeEnrolement);

        if(valeur.length > 2){
            console.log("searchId valeur = ",valeur);
            var org = "organisation="+dataInstanceEntete.organisation;
            var value = "idDreams="+valeur;

            var datavalueSearch = searchBeneficiaire+org+"&"+value;
            $http.get(datavalueSearch).then(function (response) {
                console.log("searchId() response",response);
                autocompleteData = response.data;
                chargeData();
            }, function (err) {
                console.log("searchId() err",err);
            });
        }

    };

    function getElementId(code) {
        //console.log("getElementId() code = ",code," // groupeElement = ",groupeElement);
        for(var j=0;j<groupeElement.length;j++){
            if(code == groupeElement[j].code){
                return groupeElement[j].id;
            }
        }
    }

    function chargeData() {

        $scope.valueComplete = [];
        for(var i = 0, j=autocompleteData.length;i<j;i++){
            $scope.valueComplete.push(autocompleteData[i].id_dreams);
        }
        console.log("chargeData() $scope.valueComplete = ", $scope.valueComplete);
    }

    $scope.choisir=function(string){
        $scope.groupeBenef.id_dreams=string;
        $scope.valueComplete=null;
        saveValueId(string);
    };

    function saveValueId(string) {

        for(var i =0,j=autocompleteData.length;i<j;i++){
            if(string == autocompleteData[i].id_dreams){
                gestComplete(autocompleteData[i]);

            }
        }
    }

    function gestComplete(BenefInfo) {
        $scope.groupeBenef.nomPrenomBenef = BenefInfo.name+" "+BenefInfo.firstName;


    }

    function gestElements() {
        console.log("entrer dans gestElements()");
        groupeElement = [];
        sessionElement = [];
        benefElement = [];
        autreCibleElement = [];
        for(var i =0,j=$rootScope.programmeSelect.elements.length;i<j;i++){
            var trouve = false;
            for(var l=0;l<sessionTable.length;l++){
                if(sessionTable[l] == $rootScope.programmeSelect.elements[i].element.code){
                    trouve = true;
                    sessionElement.push($rootScope.programmeSelect.elements[i].element);
                    break;
                }
            }
            if(!trouve){
                for(var l=0;l<benefTable.length;l++){
                    if(benefTable[l] == $rootScope.programmeSelect.elements[i].element.code){
                        benefElement.push($rootScope.programmeSelect.elements[i].element);
                        trouve = true;
                        break;
                    }
                }
            }

            if(!trouve){
                for(var l=0;l<autreCibleTable.length;l++){
                    if(autreCibleTable[l] == $rootScope.programmeSelect.elements[i].element.code){
                        autreCibleElement.push($rootScope.programmeSelect.elements[i].element);
                        trouve = true;
                        break;
                    }
                }
            }

            if(!trouve){
                for(var l=0;l<groupeTable.length;l++){
                    if(groupeTable[l] == $rootScope.programmeSelect.elements[i].element.code){
                        groupeElement.push($rootScope.programmeSelect.elements[i].element);
                        trouve = true;
                        break;
                    }
                }
            }


        }
        //console.log("gestElements() $rootScope.elementProgramme = ", $rootScope.elementProgramme);
        console.log("gestElements() groupeElement", groupeElement);
        console.log("gestElements() sessionElement", sessionElement);
        console.log("gestElements() benefElement", benefElement);
        console.log("gestElements() autreCibleElement", autreCibleElement);

    }

    function collecteSessionData() {
        console.log("collecteSessionData()");
        $scope.sessionData = [];
        for(var i = 0,j=sessionElement.length;i<j;i++){
            var l=0;
            while(l<instanceValue.length){
                if(sessionElement[i].id == instanceValue[l].element && instanceValue[l].value){
                    var tmp = {};
                    var numer = instanceValue[l].numero;
                    tmp.numero = numer;
                    if(sessionElement[i].ensembleOption != null){
                        tmp[sessionElement[i].code] = {};
                        tmp[sessionElement[i].code] =  chargeOptionValue(sessionElement[i].ensembleOption.options,instanceValue[l].value);
                        tmp[sessionElement[i].code]["ancVal"] = instanceValue[l];
                        if(sessionElement[i].code == "materielQuantite"){
                            tmp[sessionElement[i].code] = chargeQuantite(tmp[sessionElement[i].code]);
                        }
                    }else{
                        var tp = {};
                        tp.ancVal = {};
                        tp.ancVal = instanceValue[l];
                        tp.value = instanceValue[l].value;
                        tmp[sessionElement[i].code] = tp;
                        //tmp[sessionElement[i].code] = instanceValue[l].value;
                    }
                    $scope.sessionData = eachPosition(tmp,$scope.sessionData);
                    instanceValue.splice(l,1);
                    l--;
                }
                l++;
            }
        }
        //console.log("collecteSessionData() $scope.sessionData = ",$scope.sessionData);
        //console.log("collecteSessionData() $scope.groupe = ",$scope.groupe);
    }

    function eachPosition(objet, tableau) {
        //console.log("eachPosition()");
        //console.log("eachPosition() > objet = ",objet," / tableau = ",tableau);
        var leng = tableau.length;
        if(leng == 0){
            tableau.push(objet);
            return tableau;
        }

        var i = 0;
        while(i<leng){
            if(objet.numero == tableau[i].numero){
                var tmp = {};
                tmp = tableau[i];
                delete objet.numero;
                for (var prop in objet) {
                    tmp[prop] = objet[prop];
                }
                tableau[i] = tmp;
                return tableau;
            }
            if(objet.numero < tableau[i].numero){
                tableau.splice(i,0,objet);
                return tableau;
            }
            i++;
        }
        tableau.push(objet);
        //console.log("eachPosition tableau = ",tableau);
        return tableau;
    }

    function collecteBenefData() {
        //console.log("collecteBenefData()");
        //console.log("collecteBenefData() > benefElement = ",benefElement);
        $scope.ListBenef = [];
        for(var i = 0,j=benefElement.length;i<j;i++){
            var l=0;
            while(l<instanceValue.length){
                if(benefElement[i].id == instanceValue[l].element){
                    var tmp = {};
                    var numer = instanceValue[l].numero;
                    tmp.numero = numer;
                    if(benefElement[i].ensembleOption != null){
                        //console.log("collecteBenefData() ensembleOption existe");
                        if("totalBenefSession" == benefElement[i].code){
                            tmp[benefElement[i].code] = [];
                            tmp[benefElement[i].code] = separeSpaceParseInt(instanceValue[l].value);
                        }else{
                            //console.log("collecteBenefData() tmp[benefElement[i].code]",benefElement[i].code);
                            tmp[benefElement[i].code] = {};
                            tmp[benefElement[i].code] =  chargeOptionSet(benefElement[i].ensembleOption.options,instanceValue[l].value);
                        }

                    }else{
                        //console.log("collecteBenefData() pas de ensembleOption");
                        if("totalBenefAyantTernime" == benefElement[i].code){
                            tmp[benefElement[i].code] = parseInt(instanceValue[l].value, 10);
                        }else if("benefAyantTernime" == benefElement[i].code){
                            tmp[benefElement[i].code] = parseBoolean(instanceValue[l].value)
                        }else{
                            var tp = {};
                            tp.ancVal = {};
                            tp.ancVal = instanceValue[l];
                            tp.value = instanceValue[l].value;
                            tmp[benefElement[i].code] = tp;
                        }
                    }
                    if("totalBenefSession" != benefElement[i].code && "totalBenefAyantTernime" != benefElement[i].code){
                        //console.log("collecteBenefData() tmp = ",angular.copy(tmp));
                        $scope.ListBenef = eachPosition(tmp,$scope.ListBenef);
                    }else{
                        //console.log("collecteBenefData() tmp = ",angular.copy(tmp));
                        $scope.TotalBenef[benefElement[i].code] = tmp;
                    }
                    //console.log("collecteBenefData() $scope.ListBenef = ",angular.copy($scope.ListBenef)," // $scope.TotalBenef = ",angular.copy($scope.TotalBenef));
                    instanceValue.splice(l,1);
                    l--;
                }
                l++;
            }
        }
        console.log("collecteBenefData() $scope.ListBenef = ",$scope.ListBenef," // $scope.TotalBenef = ",$scope.TotalBenef);
    }

    function chargeOptionSet(option,value) {
       // console.log("chargeOptionSet() > option = ",option," // value = ",value);
        var tmpIndex = {};var listOption = [];
        listOption = separeSpace(value);
        for(var i = 0;i<option.length;i++){
            var trouv = false;
            for(var j = 0; j < listOption.length;j++){
                if(option[i].code == listOption[j]){
                    tmpIndex[option[i].code] = true;
                    trouv = true;
                    break;
                }
            }
            if(!trouv){
                tmpIndex[option[i].code] = false;
            }
        }
        //console.log("chargeOptionSet() > tmpIndex = ",tmpIndex);
        return tmpIndex;
    }
    function chargeOptionName(option,value) {
        console.log("chargeOptionName() > option = ",option," // value = ",value);
        var tmp = {};
        if(!value){
            return tmp;
        }
        for(var i = 0;i<option.length;i++){
            if(option[i].code == value){
                tmp.value = option[i].name;
                break;
            }
        }
        tmp.etat = chargeOptionSet(option,value);
        //console.log("chargeOptionSet() > tmpIndex = ",tmpIndex);
        return tmp;
    }

    function collecteAutreCibleData() {
        console.log("collecteAutreCibleData()");
        $scope.ListAutreCible = [];
        $scope.TotalAutreCible = {};
        for(var i = 0,j=autreCibleElement.length;i<j;i++){
            var l=0;
            while(l<instanceValue.length){
                if(autreCibleElement[i].id == instanceValue[l].element){
                    var tmp = {};
                    var numer = instanceValue[l].numero;
                    tmp.numero = numer;
                    if(autreCibleElement[i].ensembleOption != null){
                        //console.log("collecteAutreCibleData() ensembleOption existe");
                        if("totalAutreCibleSession" == autreCibleElement[i].code){
                            tmp[autreCibleElement[i].code] = [];
                            tmp[autreCibleElement[i].code] = separeSpaceParseInt(instanceValue[l].value);
                        }else if("sexeAutreCible" == autreCibleElement[i].code){
                            tmp[autreCibleElement[i].code] = {};
                            tmp[autreCibleElement[i].code] = chargeOptionName(autreCibleElement[i].ensembleOption.options,instanceValue[l].value);
                        }else{
                            tmp[autreCibleElement[i].code] = {};
                            tmp[autreCibleElement[i].code] =  chargeOptionSet(autreCibleElement[i].ensembleOption.options,instanceValue[l].value);
                        }

                    }else{
                        //console.log("collecteAutreCibleData() pas de ensembleOption");
                        if("totalAutreCibleAyantTernimeMasc" == autreCibleElement[i].code || "totalAutreCibleAyantTernimeFem" == autreCibleElement[i].code){
                            tmp[autreCibleElement[i].code] = parseInt(instanceValue[l].value, 10);
                        }else if("autreCibleAyantTernimeMasc" == autreCibleElement[i].code || "autreCibleAyantTernimeFem" == autreCibleElement[i].code){
                            tmp[autreCibleElement[i].code] = parseBoolean(instanceValue[l].value)
                        }else{
                            var tp = {};
                            tp.ancVal = {};
                            tp.ancVal = instanceValue[l];
                            tp.value = instanceValue[l].value;
                            tmp[autreCibleElement[i].code] = tp;
                        }
                    }
                    if("totalAutreCibleSession" != autreCibleElement[i].code && "totalAutreCibleAyantTernimeMasc" != autreCibleElement[i].code && "totalAutreCibleAyantTernimeFem" != autreCibleElement[i].code){
                        //console.log("collecteAutreCibleData() tmp = ",angular.copy(tmp));
                        $scope.ListAutreCible = eachPosition(tmp,$scope.ListAutreCible);
                    }else{
                        //console.log("collecteAutreCibleData() tmp = ",angular.copy(tmp));
                        $scope.TotalAutreCible[autreCibleElement[i].code] = tmp;
                    }
                    //console.log("collecteAutreCibleData() $scope.ListBenef = ",angular.copy($scope.ListBenef)," // $scope.TotalBenef = ",angular.copy($scope.TotalBenef));
                    instanceValue.splice(l,1);
                    l--;
                }
                l++;
            }
        }
        console.log("collecteBenefData() $scope.ListAutreCible = ",$scope.ListAutreCible," // $scope.TotalAutreCible = ",$scope.TotalAutreCible);
    }
    function collecteGroupeData() {
        //console.log("collecteGroupeData() groupeElement = ",angular.copy(groupeElement)," // instanceValue = ",angular.copy(instanceValue));
        $scope.groupe = {};
        for(var i = 0,j=groupeElement.length;i<j;i++){
            var l=0;
            while(l<instanceValue.length){
                if(groupeElement[i].id == instanceValue[l].element){
                    //console.log("collecteGroupeData() code = ",groupeElement[i].code," //instanceValue[l] = ",angular.copy(instanceValue[l]));
                    $scope.groupe[groupeElement[i].code] = instanceValue[l].value;
                    $scope.groupe.numero = instanceValue[l].numero;
                    instanceValue.splice(l,1);
                    l--;
                    break;
                }
                l++;
            }
        }
        console.log("collecteGroupeData() $scope.groupe = ",angular.copy($scope.groupe));
    }

    function chargeOption(ensemble) {
        //console.log("entrer dans chargeOption > ensemble = ",ensemble);
        for(var i=0, ij = $rootScope.ensembleOption.length;i<ij;i++){
            if(ensemble.id == $rootScope.ensembleOption[i].id){
                ensemble.options = $rootScope.ensembleOption[i].options;
                return ensemble;
            }
        }
    }

    function chargeOptionValue(option,data) {
        //console.log("entrer dans chargeOptionValue");
        //console.log("chargeOptionValue() > option = ",option," || data = ",data);
        var tmp = {};
        tmp.origValue = [];
        tmp.origValue = separeSpace(data);
            tmp.value = [];
        ////console.log("chargeOptionValue() > origValue = ",origValue);
        tmp.value = getValue(option,tmp.origValue);
        //console.log("chargeOptionValue() > tmp = ",tmp);
        return tmp;

    }

    function separeSpace(data) {
        var lesData = [];
        if(!data){return lesData;}
        var conti = true;
        var space = -2,initi = 0;
        while (conti){
            space = data.indexOf(" ",initi);
            if(space != -1){
                lesData.push(data.substring(initi, space));
                initi = space+1;
            }else{
                lesData.push(data.substring(initi, data.length));
                conti = false;
            }
        }
        return lesData;
    }
    function separeSpaceParseInt(data) {
        var lesData = [];
        if(!data){return lesData;}
        var conti = true;
        var space = -2,initi = 0;
        while (conti){
            space = data.indexOf(" ",initi);
            if(space != -1){
                lesData.push(parseInt(data.substring(initi, space), 10));
                initi = space+1;
            }else{
                lesData.push(parseInt(data.substring(initi, data.length), 10));
                conti = false;
            }
        }
        return lesData;
    }
    function parseBoolean(data) {
        if(data == 'true')
            return true;
        if(data == 'false')
            return false;
        return null;
    }
    function getValue(option,origValue) {
        //console.log("entrer dans getValue");
        //console.log("getValue() > option = ",option," origValue = ",origValue);
        var value = [];
        for(var i = 0;i<origValue.length;i++){
            var trouv = false;
            for(var k=0;k<option.length;k++){
                if(option[k].code == origValue[i]){
                    trouv = true;
                    value.push(option[k].name);
                    break;
                }
            }
            if(!trouv){
                value.push(origValue[i]);
            }

        }
        return value;
    }

    function chargeQuantite(tmp) {
        //console.log("chargeQuantite() enter tmp = ",angular.copy(tmp));
        var a = 0; var tab = [];
        while(a < tmp.value.length){
            var val = "";
            val = tmp.value[a]+" "+tmp.value[a + 1];
            tab.push(val);
            a += 2;
        }
        tmp.value = tab;
        //console.log("chargeQuantite() sortie tmp = ",angular.copy(tmp));
        return tmp;
    }
    
    $scope.MQfocus = function () {
        //console.log("MQfocus()");
        $(function () {
            // wait till load event fires so all resources are available
            $('#modalLoginForm').modal('show');
        });
    };
    $scope.outilfocus = function () {
        //console.log("outilfocus()");
        $(function () {
            // wait till load event fires so all resources are available
            $('#modalOutil').modal('show');
        });
    };
    $scope.methodfocus = function () {
        //console.log("methodfocus()");
        $(function () {
            // wait till load event fires so all resources are available
            $('#modalMethod').modal('show');
        });
    };
    $scope.saveOutils = function () {
        //console.log("saveOutils () $scope.outils",$scope.outil);
        //console.log("saveOutils () sessionElement",sessionElement);
        var options = [];
        options = getOption(sessionElement,"outils");
        $scope.groupeSession.outils = "";
        $scope.outilsInfo = "";
        for (var prop in $scope.outil) {
            if($scope.outil[prop]){
                if($scope.groupeSession.outils == ""){
                    $scope.groupeSession.outils = prop;
                    $scope.outilsInfo = getOptionValue(options,prop);
                }
                else{
                    $scope.groupeSession.outils = $scope.groupeSession.outils +" "+prop;
                    $scope.outilsInfo = $scope.outilsInfo+"; "+getOptionValue(options,prop);
                }
            }
        }
    };

    function getOptionValue(option,value) {
        //console.log("entrer dans getOptionValue");
            for(var k=0;k<option.length;k++){
                if(option[k].code == value){
                    return option[k].name;
                }
        }
        return value;
    }
    function getOption(element,code) {
        //console.log("entrer dans getOptionValue");
        for(var k=0;k<element.length;k++){
            if(element[k].code == code){
                return element[k].ensembleOption.options;
            }
        }
        return value;
    }

    $scope.saveMethode = function () {
        //console.log("saveMethode () $scope.method",$scope.method);
        //console.log("saveMethode () sessionElement",sessionElement);
        var options = [];
        options = getOption(sessionElement,"methodologie");
        $scope.groupeSession.methodologie = "";
        $scope.methodInfo = "";
        for (var prop in $scope.method) {
            if($scope.method[prop]){
                if($scope.groupeSession.methodologie == ""){
                    $scope.groupeSession.methodologie = prop;
                    $scope.methodInfo = getOptionValue(options,prop);
                }
                else{
                    $scope.groupeSession.methodologie = $scope.groupeSession.methodologie +" "+prop;
                    $scope.methodInfo = $scope.methodInfo+"; "+getOptionValue(options,prop);
                }
            }
        }
    };
    $scope.modifierSession = function (session) {
        //console.log("modifierSession() > session = ",session);
        iniSession();
        modifSession = session;
        $scope.groupeSession.dateSession = session.dateSession.value;
         $scope.groupeSession.themeAborde = session.themeAborde.value;
        MethoModif(session.methodologie.origValue);
        if(session.outils){
            OutilsModif(session.outils.origValue);
        }
        if(session.materielQuantite){
            MaterielQuantiteModif(session.materielQuantite.origValue);
        }

         /*$scope.groupeSession.methodologie = [];
         $scope.groupeSession.methodologie = session.methodologie.origValue;
         $scope.groupeSession.outils = [];
         $scope.groupeSession.outils = session.outils.origValue;*/
    };

    function MethoModif(origine) {
        $scope.method = {};
        for(var i=0;i<origine.length;i++){
            $scope.method[origine[i]] = true;
        }
        $scope.saveMethode();
    }
    function OutilsModif(origine) {
        $scope.outil = {};
        for(var i=0;i<origine.length;i++){
            $scope.outil[origine[i]] = true;
        }
        $scope.saveOutils();
    }
    function MaterielQuantiteModif(origine) {
        $scope.materiel = {};
        for(var i=0;i<origine.length;i+=2){
            //console.log('i = ',i,"origine.length = ",origine.length);
            $scope.materiel[origine[i+1]] = true;
            $scope[origine[i+1]] = origine[i];
            materielQte[origine[i+1]] = origine[i];
        }
        $scope.saveMaterial();
    }
    function isEmpty(obj) {
        for(var prop in obj) {
            if(obj.hasOwnProperty(prop))
                return false;
        }
        return true;
    }

    $scope.saveBenef = function () {
        console.log("saveBenef()");
        console.log("saveBenef() groupeBenef = ",$scope.groupeBenef);
        console.log("saveBenef() benefElement = ",benefElement);
        console.log("saveBenef() dataInstance = ",dataInstance);
        if(checkbenef() && dataInstance.instance){
            origin = "benef";
            //console.log("saveBenef() $scope.sessionData = ",$scope.sessionData);
            dataInstance.dreamsId = $scope.groupeBenef.id_dreams;
            dataInstance.dateActivite = $scope.sessionData[0].dateSession.value;
            dataInstance.order = 1;
            var code = Object.keys($scope.groupeBenef);
            dataInstance.dataValue = [];
            for(var pop in $scope.groupeBenef){
                if(!benefValue[pop]){
                    benefValue[pop] = getBenfElementId(pop);
                }
                var data = {};
                //data.numero = modifSession.numero;
                data.value = $scope.groupeBenef[pop];
                data.element = benefValue[pop];
                dataInstance.dataValue.push(data);
            }
            if(dataInstance.dataValue.length != 0){
                console.log("saveBenef() dataInstance = ",dataInstance);
                saveData();
            }
        }
        initBenef();
    };

    function checkbenef() {
        if(!$scope.groupeBenef.id_dreams && !isExiste()){
            return false;
        }
        if(!$scope.groupeBenef.nomPrenomBenef){
            return false;
        }
        if(!$scope.groupeBenef.contactBenef){
            $scope.groupeBenef.contactBenef = null;
        }
        $scope.groupeBenef.benefSession = null;
        $scope.groupeBenef.benefAyantTernime = false;

        if($scope.ListBenef.length == 0){
            $scope.groupeBenef.totalBenefSession = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
            $scope.groupeBenef.totalBenefAyantTernime = 0;
        }
        console.log("checkbenef() > $scope.groupeBenef = ",$scope.groupeBenef);
        return true;
    }
    function initBenef() {
        $scope.groupeBenef = {}
    }

    function isExiste() {
        for(var i = 0,j=instanceValue.length;i<j;i++){
            if(benefID.idDreams == instanceValue[i].element){
                return true;
            }
        }
        return false;
    }

    function getBenfElementId(code) {
        //console.log("getElementId() code = ",code," // groupeElement = ",groupeElement);
        for(var j=0;j<benefElement.length;j++){
            if(code == benefElement[j].code){
                return benefElement[j].id;
            }
        }
    }
    function getAutreCibleElementId(code) {
        //console.log("getElementId() code = ",code," // groupeElement = ",groupeElement);
        for(var j=0;j<autreCibleElement.length;j++){
            if(code == autreCibleElement[j].code){
                return autreCibleElement[j].id;
            }
        }
    }

    $scope.changeSessionBenef = function (benef,key,value,index) {
        console.log("entrer dans changeSessionBenef() > benef= ",angular.copy(benef),"// key = ",key," // value = ",value," // index = ",index);
        benef.benefSession[key] = value;
        //console.log("changeSessionBenef() > benef= ",angular.copy(benef));
        //console.log("changeSessionBenef() > $scope.ListBenef= ",angular.copy($scope.ListBenef)," // $scope.TotalBenef = ",$scope.TotalBenef);
        if(dataInstance.instance && index < $scope.sessionData.length){
            origin = "benef";
            console.log("changeSessionBenef() $scope.sessionData = ",$scope.sessionData);
            dataInstance.dreamsId = benef.id_dreams.value;
            dataInstance.dateActivite = $scope.sessionData[index].dateSession.value;
            dataInstance.order = index + 1;
            dataInstance.codeId = null;
            if(value){
                dataInstance.organisation = "ajouter";
            }else{
                dataInstance.organisation = "delete";
            }
            dataInstance.dataValue = [];
            var benefSes = "";
            for(var pop in benef.benefSession){
                if(benef.benefSession[pop]){
                    if(benefSes == ""){
                        benefSes = pop;
                    }else{
                        benefSes += " "+ pop;
                    }
                }
            }
            var data = {};
            data.numero = benef.numero;
            data.value = benefSes;
            data.element = getBenfElementId("benefSession");
            dataInstance.dataValue.push(data);

            if(value){
                $scope.TotalBenef.totalBenefSession.totalBenefSession[index] += 1;
            }else{
                $scope.TotalBenef.totalBenefSession.totalBenefSession[index] -= 1;
            }
            var totalBenefSes = "";
            for(var i =0;i<$scope.TotalBenef.totalBenefSession.totalBenefSession.length;i++){
                if(totalBenefSes == ""){
                    totalBenefSes = ""+$scope.TotalBenef.totalBenefSession.totalBenefSession[i];
                }else{
                    totalBenefSes += " "+ $scope.TotalBenef.totalBenefSession.totalBenefSession[i];
                }
            }
            var data = {};
            data.numero = $scope.TotalBenef.totalBenefSession.numero;
            data.value = totalBenefSes;
            data.element = getBenfElementId("totalBenefSession");
            dataInstance.dataValue.push(data);



            if(dataInstance.dataValue.length != 0){
                console.log("changeSessionBenef() dataInstance = ",dataInstance);
                saveData();
            }
        }
    };
    
    $scope.changeSessionBenefTermine = function (benef,value) {
        //console.log("entrer dans changeSessionBenefTermine() > benef= ",angular.copy(benef)," // value = ",value);
        if(dataInstance.instance){
            origin = "benef";
            dataInstance.dataValue = [];
            var data = {};
            data.numero = benef.numero;
            data.value = benef.benefAyantTernime;
            data.element = getBenfElementId("benefAyantTernime");
            dataInstance.dataValue.push(data);

            dataInstance.dreamsId = benef.id_dreams.value;
            dataInstance.dateActivite = $scope.sessionData[0].dateSession.value;
            dataInstance.order = 1;
            dataInstance.codeId = data.element;

            if(value){
                dataInstance.organisation = "ajouter";
            }else{
                dataInstance.organisation = "delete";
            }

            if(value){
                $scope.TotalBenef.totalBenefAyantTernime.totalBenefAyantTernime += 1;
            }else{
                $scope.TotalBenef.totalBenefAyantTernime.totalBenefAyantTernime -= 1;
            }
           var data = {};
            data.numero = $scope.TotalBenef.totalBenefAyantTernime.numero;
            data.value = $scope.TotalBenef.totalBenefAyantTernime.totalBenefAyantTernime;
            data.element = getBenfElementId("totalBenefAyantTernime");
            dataInstance.dataValue.push(data);

            if(dataInstance.dataValue.length != 0){
                console.log("changeSessionBenef() dataInstance = ",dataInstance);
                saveData();
            }
        }
    };

    $scope.searchCode = function (valeur) {
        console.log("searchCode valeur = ",valeur);
        if(valeur.length > 2){
            console.log("searchId valeur = ",valeur);
            var org = "organisation="+dataInstanceEntete.organisation;
            var value = "idDreams="+valeur;
            var datavalueSearch = searchBeneficiaire+org+"&"+value;
            $http.get(datavalueSearch).then(function (response) {
                console.log("searchId() response",response);
                autocompleteData = response.data;
                chargeDataCode();
            }, function (err) {
                console.log("searchId() err",err);
            });
        }

    };

    function chargeDataCode() {
        $scope.valueCompleteCode = [];
        $scope.valueCompleteCode = [];
        for(var i = 0, j=autocompleteData.length;i<j;i++){
            $scope.valueCompleteCode.push(autocompleteData[i].id_dreams);
        }
    }

    $scope.choisirCode=function(string){
        $scope.groupeAutreCible.codeBenef=string;
        $scope.valueCompleteCode=null;
    };


    $scope.saveAutreCible = function () {
        console.log("saveAutreCible()");
        console.log("saveAutreCible() groupeAutreCible = ",$scope.groupeAutreCible);
        console.log("saveAutreCible() autreCibleElement = ",autreCibleElement);
        console.log("saveAutreCible() dataInstance = ",dataInstance);
        if(checkAutreCible() && dataInstance.instance){
            origin = "autreCible";
            /*dataInstance.dreamsId = $scope.groupeAutreCible.codeBenef;
            dataInstance.dateActivite = $scope.sessionData[0].dateSession.value;
            dataInstance.order = 1;*/
            dataInstance.dataValue = [];
            for(var pop in $scope.groupeAutreCible){
                if(!autreCibleValue[pop]){
                    autreCibleValue[pop] = getAutreCibleElementId(pop);
                }
                var data = {};
                //data.numero = modifSession.numero;
                data.value = $scope.groupeAutreCible[pop];
                data.element = autreCibleValue[pop];
                dataInstance.dataValue.push(data);
            }
            if(dataInstance.dataValue.length != 0){
                console.log("saveBenef() dataInstance = ",dataInstance);
                saveData();
            }
        }
        initAutreCible();
    };

    function checkAutreCible() {
        if(!$scope.groupeAutreCible.codeBenef && !isCodeExiste($scope.groupeAutreCible.codeBenef)){
            return false;
        }
        if(!$scope.groupeAutreCible.nomPrenomAutreCible){
            return false;
        }
        if(!$scope.groupeAutreCible.sexeAutreCible){
            return false;
        }
        if(!$scope.groupeAutreCible.contactAutreCible){
            $scope.groupeAutreCible.contactAutreCible = null;
        }


        $scope.groupeAutreCible.autreCibleSession = null;

        if($scope.groupeAutreCible.sexeAutreCible == "M"){
            $scope.groupeAutreCible.autreCibleAyantTernimeMasc = false;
            $scope.groupeAutreCible.autreCibleAyantTernimeFem = null;
        }else{
            $scope.groupeAutreCible.autreCibleAyantTernimeMasc = null;
            $scope.groupeAutreCible.autreCibleAyantTernimeFem = false;
        }

        if($scope.ListAutreCible.length == 0){
            $scope.groupeAutreCible.totalAutreCibleSession = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
            $scope.groupeAutreCible.totalAutreCibleAyantTernimeMasc = 0;
            $scope.groupeAutreCible.totalAutreCibleAyantTernimeFem = 0;
        }
        console.log("checkAutreCible() > $scope.groupeAutreCible = ",$scope.groupeAutreCible);
        return true;
    }

    function isCodeExiste(code) {
        for(var i = 0,j=autocompleteData.length;i<j;i++){
            if(code == autocompleteData[i].value){
                return true;
            }
        }
        return false;
    }
    function initAutreCible() {
        $scope.groupeAutreCible = {}
    }
    $scope.changeSessionAutre = function (autre,key,value,index) {
        console.log('changeSessionAutre() > autre = ',autre," // key = ",key," // value = ",value," // index= ",index);
        //console.log("entrer dans changeSessionBenef() > benef= ",angular.copy(benef),"// key = ",key," // value = ",value," // index = ",index);
        autre.autreCibleSession[key] = value;
        if(dataInstance.instance){
            origin = "autreCible";
            dataInstance.dataValue = [];
            var autreCibleSess = "";
            for(var pop in autre.autreCibleSession){
                if(autre.autreCibleSession[pop]){
                    if(autreCibleSess == ""){
                        autreCibleSess = pop;
                    }else{
                        autreCibleSess += " "+ pop;
                    }
                }
            }
            var data = {};
            data.numero = autre.numero;
            data.value = autreCibleSess;
            data.element = getAutreCibleElementId("autreCibleSession");
            dataInstance.dataValue.push(data);

            if(value){
                $scope.TotalAutreCible.totalAutreCibleSession.totalAutreCibleSession[index] += 1;
            }else{
                $scope.TotalAutreCible.totalAutreCibleSession.totalAutreCibleSession[index] -= 1;
            }
            var totalautreCibleSess = "";
            for(var i =0;i<$scope.TotalAutreCible.totalAutreCibleSession.totalAutreCibleSession.length;i++){
                if(totalautreCibleSess == ""){
                    totalautreCibleSess = ""+$scope.TotalAutreCible.totalAutreCibleSession.totalAutreCibleSession[i];
                }else{
                    totalautreCibleSess += " "+$scope.TotalAutreCible.totalAutreCibleSession.totalAutreCibleSession[i];
                }
            }
            var data = {};
            data.numero = $scope.TotalAutreCible.totalAutreCibleSession.numero;
            data.value = totalautreCibleSess;
            data.element = getAutreCibleElementId("totalAutreCibleSession");
            dataInstance.dataValue.push(data);



            if(dataInstance.dataValue.length != 0){
                console.log("changeSessionBenef() dataInstance = ",dataInstance);
                saveData();
            }
        }

    };

    $scope.changeSessionAutreTermineMasc = function (autre, value) {
        //console.log("changeSessionAutreTermineMasc() autre = ",autre," // value = ",value," // $scope.TotalAutreCible= ",$scope.TotalAutreCible);
        if(dataInstance.instance){
            origin = "autreCible";
            dataInstance.dataValue = [];
            var data = {};
            data.numero = autre.numero;
            data.value = autre.autreCibleAyantTernimeMasc;
            data.element = getAutreCibleElementId("autreCibleAyantTernimeMasc");
            dataInstance.dataValue.push(data);

            if(value){
                $scope.TotalAutreCible.totalAutreCibleAyantTernimeMasc.totalAutreCibleAyantTernimeMasc += 1;
            }else{
                $scope.TotalAutreCible.totalAutreCibleAyantTernimeMasc.totalAutreCibleAyantTernimeMasc -= 1;
            }
            var data = {};
            data.numero = $scope.TotalAutreCible.totalAutreCibleAyantTernimeMasc.numero;
            data.value = $scope.TotalAutreCible.totalAutreCibleAyantTernimeMasc.totalAutreCibleAyantTernimeMasc;
            data.element = getAutreCibleElementId("totalAutreCibleAyantTernimeMasc");
            dataInstance.dataValue.push(data);

            if(dataInstance.dataValue.length != 0){
                console.log("changeSessionBenef() dataInstance = ",dataInstance);
                saveData();
            }
        }
    };

    $scope.changeSessionAutreTermineFem = function (autre, value) {
        //console.log("changeSessionAutreTermineMasc() autre = ",autre," // value = ",value," // $scope.TotalAutreCible= ",$scope.TotalAutreCible);
        if(dataInstance.instance){
            origin = "autreCible";
            dataInstance.dataValue = [];
            var data = {};
            data.numero = autre.numero;
            data.value = autre.autreCibleAyantTernimeFem;
            data.element = getAutreCibleElementId("autreCibleAyantTernimeFem");
            dataInstance.dataValue.push(data);

            if(value){
                $scope.TotalAutreCible.totalAutreCibleAyantTernimeFem.totalAutreCibleAyantTernimeFem += 1;
            }else{
                $scope.TotalAutreCible.totalAutreCibleAyantTernimeFem.totalAutreCibleAyantTernimeFem -= 1;
            }
            var data = {};
            data.numero = $scope.TotalAutreCible.totalAutreCibleAyantTernimeFem.numero;
            data.value = $scope.TotalAutreCible.totalAutreCibleAyantTernimeFem.totalAutreCibleAyantTernimeFem;
            data.element = getAutreCibleElementId("totalAutreCibleAyantTernimeFem");
            dataInstance.dataValue.push(data);

            if(dataInstance.dataValue.length != 0){
                console.log("changeSessionBenef() dataInstance = ",dataInstance);
                saveData();
            }
        }
    }

}]);