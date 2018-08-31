saisie.controller('enrol_SF_Ctrl', ['$scope', '$rootScope', '$stateParams', '$http','$filter','$state', function ($scope, $rootScope, $stateParams, $http,$filter,$state) {
    console.log("entrer dans enrol_SF_Ctrl");
    var ValueUrl = serverAdresse+'dataValue';
	var dataInstanceEntete = {};
    var dataInstance = {};
    dataInstanceEntete.programme = $stateParams.prog;
    dataInstanceEntete.organisation = $stateParams.org;
    dataInstanceEntete.instance = $stateParams.inst;
    dataInstance = angular.copy(dataInstanceEntete);
	$scope.enrolF = {};
	var enrolSectionF = ["_01_deja_enceinte",
	"_02_enceinte",
	"_03_suivi_gross",
	"_03_a_nom_hopital",
	"_04_method_pf",
	"_04_a_autre_method_pf",
	"_05_method_pf_util",
	"_05_a_autre_method_pf_util",
	"_06_raison_method_pf_non_util",
	"_06_a_autre_raison_method_pf_non_util"];
	
	mappigData();
	
	
	 function mappigData() {
	        for(var i = 0;i<enrolSectionF.length;i++){
	            var id = getElementId(enrolSectionF[i]);
	            if(id){
	                for(var j=0;j<$rootScope.enrolementData.length;j++){
	                    if(id == $rootScope.enrolementData[j].element){
	                        $scope.enrolF[enrolSectionF[i]] = $rootScope.enrolementData[j].value;
	                    }
	                }
	            }
	        }
	        console.log("mappigData() $scope.enrolF = ",$scope.enrolF);
	    }
	
	$scope.savePage = function (){
		console.log("entrer dans savePage");
		console.log("$scope.enrolF = ",$scope.enrolF);
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
		$state.go('enrol_SE',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
		
	}
	
	function getElement() {
        console.log("getElement()");
        $scope.enrolF._04_method_pf = gestmethodPf();
        $scope.enrolF._05_method_pf_util = gestmethodPfUtil();
        console.log("$scope.enrolF = ",$scope.enrolF);
        for(var pop in $scope.enrolF){
            if($scope.enrolF[pop] != null && $scope.enrolF[pop] && $scope.enrolF[pop] != ""){
                var id = getElementId(pop);
                if(id){
                    var data = {};
                    data.element = id;
                    data.value = $scope.enrolF[pop];
                    data.numero = 1;
                    dataInstance.dataValue.push(data);
                }else{
                    console.error("getElement(). Element non trouvé, code = ",pop);
                }
            }else {
                console.info("getElement(). Element sans valeur, code = ",pop," // valeur = ",$scope.enrolF[pop]);
            }
        }
        console.log("dataInstance = ",dataInstance);

    }
	
	function gestmethodPf(){
		console.log("$scope.methodPf = ",$scope.methodPf);
		var methodPfValue=null;
		 for(var pop in $scope.methodPf){
			 if($scope.methodPf[pop]){
				if(!methodPfValue){
					methodPfValue= "" + pop;
				} 
				else{
					methodPfValue= methodPfValue +" " + pop;
				}
			 }
		 }
		 console.log("methodPfValue = ",methodPfValue);
		 return methodPfValue;
	}
	
	function gestmethodPfUtil(){
		console.log("$scope.methodPfUtil = ",$scope.methodPfUtil);
		var methodPfUtilValue=null;
		 for(var pop in $scope.methodPfUtil){
			 if($scope.methodPfUtil[pop]){
				if(!methodPfUtilValue){
					methodPfUtilValue= "" + pop;
				} 
				else{
					methodPfUtilValue= methodPfUtilValue +" " + pop;
				}
			 }
		 }
		 console.log("methodPfUtilValue = ",methodPfUtilValue);
		 return methodPfUtilValue;
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
        $state.go('enrol_SG',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
    }
    
    
    
}]);