saisie.controller('enrol_SH_Ctrl', ['$scope', '$rootScope', '$stateParams', '$http','$filter','$state', function ($scope, $rootScope, $stateParams, $http,$filter,$state) {
    console.log("entrer dans enrol_SH_Ctrl");
    var ValueUrl = serverAdresse+'dataValue';
	var dataInstanceEntete = {};
    var dataInstance = {};
    dataInstanceEntete.programme = $stateParams.prog;
    dataInstanceEntete.organisation = $stateParams.org;
    dataInstanceEntete.instance = $stateParams.inst;
    dataInstance = angular.copy(dataInstanceEntete);
	$scope.enrolH = {};
	var enrolSectionH = ["_01_conso_alcool",
		"_02_freq_conso_alcool",
		"_03_raison_conso_alcool",
		"_03_a_autre_raison_conso_alcool",
		"_04_conso_drogue",
		"_05_type_drogue",
		"_05_a_autre_type_drogue",
		"_06_raison_conso_drogue",
		"_06_a_autre_raison_conso_drogue"];
	
	mappigData();
	
	
		 function mappigData() {
		        for(var i = 0;i<enrolSectionH.length;i++){
		            var id = getElementId(enrolSectionH[i]);
		            if(id){
		                for(var j=0;j<$rootScope.enrolementData.length;j++){
		                    if(id == $rootScope.enrolementData[j].element){
		                        $scope.enrolH[enrolSectionH[i]] = $rootScope.enrolementData[j].value;
		                    }
		                }
		            }
		        }
		        console.log("mappigData() $scope.enrolH = ",$scope.enrolH);
		    }
	
	$scope.savePage = function (){
		console.log("entrer dans savePage");
		console.log("$scope.enrolH = ",$scope.enrolH);
		console.log("dataInstance = ",dataInstance);
		console.log("$rootScope.programmeSelect = ",$rootScope.programmeSelect);
		//dataInstance.dreamsId = $scope.enrolA.id_dreams;
        //dataInstance.dateActivite = $scope.enrolA.dat_enrol;
		dataInstance.dataValue = [];
		getElement();
		saveData();
		
	}
	
	$scope.previewPage = function (){
		console.log("entrer dans previewPage");
		$state.go('enrol_SG',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
		
	}
	
	function getElement() {
        console.log("getElement()");
        $scope.enrolH._05_type_drogue = gesttypeDrogue();
        $scope.enrolH._06_raison_conso_drogue = gestraisonConsoDrogue();
        console.log("$scope.enrolH = ",$scope.enrolH);
        for(var pop in $scope.enrolH){
            if($scope.enrolH[pop] != null && $scope.enrolH[pop] && $scope.enrolH[pop] != ""){
                var id = getElementId(pop);
                if(id){
                    var data = {};
                    data.element = id;
                    data.value = $scope.enrolH[pop];
                    data.numero = 1;
                    dataInstance.dataValue.push(data);
                }else{
                    console.error("getElement(). Element non trouvé, code = ",pop);
                }
            }else {
                console.info("getElement(). Element sans valeur, code = ",pop," // valeur = ",$scope.enrolH[pop]);
            }
        }
        console.log("dataInstance = ",dataInstance);

    }
	
	function gesttypeDrogue(){
		console.log("$scope.typeDrogue = ",$scope.typeDrogue);
		var typeDrogueValue=null;
		 for(var pop in $scope.typeDrogue){
			 if($scope.typeDrogue[pop]){
				if(!typeDrogueValue){
					typeDrogueValue= "" + pop;
				} 
				else{
					typeDrogueValue= typeDrogueValue +" " + pop;
				}
			 }
		 }
		 console.log("typeDrogueValue = ",typeDrogueValue);
		 return typeDrogueValue;
	}
	
	function gestraisonConsoDrogue(){
		console.log("$scope.raisonConsoDrogue = ",$scope.raisonConsoDrogue);
		var raisonConsoDrogueValue=null;
		 for(var pop in $scope.raisonConsoDrogue){
			 if($scope.raisonConsoDrogue[pop]){
				if(!raisonConsoDrogueValue){
					raisonConsoDrogueValue= "" + pop;
				} 
				else{
					raisonConsoDrogueValue= raisonConsoDrogueValue +" " + pop;
				}
			 }
		 }
		 console.log("raisonConsoDrogueValue = ",raisonConsoDrogueValue);
		 return raisonConsoDrogueValue;
	}
	
	function getElementId(code) {
        for(var j = 0;j<$rootScope.programmeSelect.elements.length;j++){
            if($rootScope.programmeSelect.elements[j].element.code == code){
                return $rootScope.programmeSelect.elements[j].element.id;
            }
        }
        return null;
    }
	
	function saveData() {

        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(ValueUrl, dataInstance, config).then(function(succes){
            console.log("saveData() succes = ",succes);
            //dataInstance.instance =
            if(succes.data.status == "ok"){
                //dataInstance.instance = succes.data.id;
                succesSave();
            }else{
                toastr["success"]("Echec d'enregistrement");
            }
        }, function(error){
            console.log("saveData() error = ",error);
            toastr["success"]("Echec d'enregistrement");
        });
    }
	
	function succesSave() {
        $state.go('enrol_SIJK',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
    }
    
    
    
}]);