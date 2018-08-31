saisie.controller('enrol_SC_Ctrl', ['$scope', '$rootScope', '$stateParams', '$http','$filter','$state', function ($scope, $rootScope, $stateParams, $http,$filter,$state) {
    console.log("entrer dans enrol_SC_Ctrl");
    var ValueUrl = serverAdresse+'dataValue';
	var dataInstanceEntete = {};
    var dataInstance = {};
    dataInstanceEntete.programme = $stateParams.prog;
    dataInstanceEntete.organisation = $stateParams.org;
    dataInstanceEntete.instance = $stateParams.inst;
    dataInstance = angular.copy(dataInstanceEntete);
	$scope.enrolC = {};
	var enrolSectionC = ["_01_scolarise","_02_niv_scolaire","_02_a_classe","_02_a_autre_niv_scolaire",
		"_03_soutient_finance""_03_a_autre_soutient_finance","_04_pas_sco_actuel","_05_raison_non_sco",
		"_05_a_autre_raison_non_sco","_06_second_chance","_06_a_autre_second_chance","_07_source_revenu",
		"_07_a_autre_source_revenu","_08_economie","_09_gard_economie","_09_a_autre_gard_economie"];
	
	mappigData();
	
	
	 function mappigData() {
	        for(var i = 0;i<enrolSectionC.length;i++){
	            var id = getElementId(enrolSectionC[i]);
	            if(id){
	                for(var j=0;j<$rootScope.enrolementData.length;j++){
	                    if(id == $rootScope.enrolementData[j].element){
	                        $scope.enrolC[enrolSectionC[i]] = $rootScope.enrolementData[j].value;
	                    }
	                }
	            }
	        }
	        console.log("mappigData() $scope.enrolC = ",$scope.enrolC);
	    }
	
	$scope.savePage = function (){
		console.log("entrer dans savePage");
		console.log("$scope.enrolC = ",$scope.enrolC);
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
		$state.go('enrol_SB',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
		
	}
	
	function getElement() {
        console.log("getElement()");
        for(var pop in $scope.enrolC){
            if($scope.enrolB[pop] != null && $scope.enrolB[pop] && $scope.enrolB[pop] != ""){
                var id = getElementId(pop);
                if(id){
                    var data = {};
                    data.element = id;
                    data.value = $scope.enrolB[pop];
                    data.numero = 1;
                    dataInstance.dataValue.push(data);
                }else{
                    console.error("getElement(). Element non trouvé, code = ",pop);
                }
            }else {
                console.info("getElement(). Element sans valeur, code = ",pop," // valeur = ",$scope.enrolC[pop]);
            }
        }
        console.log("dataInstance = ",dataInstance);

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
        $state.go('enrol_SD',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
    }
    
    
    
}]);