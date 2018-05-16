var app = angular.module('UsersApp', ['ui.materialize'])
    .controller('UserController', ["$scope","$http", "$timeout", function ($scope, $http, $timeout) {
        $scope.users = [];
        $scope.new = {};

        $scope.listar = function () {
            $http.get('/users')
                .success(function(data) {
                    //console.log(data);
                    $scope.users = data;
                })
                .error(function(err) {
                    console.log(err);
                });
        }

        $scope.select = {
            value: "true",
            choices: ["true","false"]
        };

        $scope.clearForm = function () {
            $scope.new = {};
            $scope.currentTime = '';
            $scope.select.value = "true";
        }

        var currentTime = new Date();
        $scope.currentTime = currentTime;
        $scope.month = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
        $scope.monthShort = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
        $scope.weekdaysFull = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
        $scope.weekdaysLetter = ['S', 'M', 'T', 'W', 'T', 'F', 'S'];
        $scope.disable = [false, 1, 7];
        $scope.today = 'Today';
        $scope.clear = 'Clear';
        $scope.close = 'Close';
        var days = 1;
        //$scope.minDate = (new Date($scope.currentTime.getTime() - ( 1000 * 60 * 60 *24 * days ))).toISOString();
        $scope.maxDate = (new Date($scope.currentTime.getTime() + ( 1000 * 60 * 60 *24 * days ))).toISOString();
        $scope.onStart = function () {
            console.log('onStart');
        };
        $scope.onRender = function () {
            console.log('onRender');
        };
        $scope.onOpen = function () {
            console.log('onOpen');
        };
        $scope.onClose = function () {
            console.log('onClose');
        };
        $scope.onSet = function () {
            console.log('onSet');
        };
        $scope.onStop = function () {
            console.log('onStop');
        };

        //call onload html
        angular.element(document).ready(function () {
            $scope.$apply( function() {
                //prepara os combobox
                document.addEventListener('DOMContentLoaded', function() {
                    var elems 	= document.querySelectorAll('select');
                    var options = document.querySelectorAll('option');
                    var instances = M.FormSelect.init(elems, options);
                });
            });

            $scope.currentTime = '';
            $scope.listar();
        });

        $scope.delUser = function ( id ) {
            $http({
                method: 'DELETE',
                url: '/users/' + id
            }).then(function(response) {
                //console.log(response);
                $scope.listar();

                }, function(error) {
                    console.log(error);
                });
        }

        $scope.putDataUser = function (id) {
            $http.get('/users/' + id)
                .success(function(data) {
                    //console.log(data);
                    $scope.new.id = data.id;
                    $scope.new.name = data.name;
                    $scope.new.surname = data.surname;
                    $scope.select.value = data.enabled;
                    $scope.new.username = data.username;
                    $scope.new.password = data.password;
                    $scope.currentTime = data.dateFmt;
                    $scope.new.email = data.email;
                    $scope.new.phone = data.phone;

                })
                .error(function(err) {
                    console.log(err);
                });
        }

        $scope.editUser = function () {
            var user = {
                "id" : $scope.new.id,
                "name" : $scope.new.name,
                "surname" : $scope.new.surname,
                "enabled" : $scope.select.value,
                "username" : $scope.new.username,
                "password" : $scope.new.password,
                "email" : $scope.new.email,
                "phone" : $scope.new.phone,
                "formatedDate" : $scope.currentTime
                //"data$scope.currentTime = data.dateFmt;
            }

            $http({
                method: 'PUT',
                url: '/users',
                data : JSON.stringify(user),
                headers: {"Content-Type": "application/json;charset=UTF-8"}

            }).then(function(response) {
                //console.log(response);
                $scope.listar();

                $scope.clearForm();

            }, function(error) {
                console.log(error);
            });
        }


    }]);

app.directive('focusMe', function($timeout) {
    return {
        scope: { trigger: '@focusMe' },
        link: function(scope, element) {
            scope.$watch('trigger', function(value) {
                if(value === "true") {
                    $timeout(function() {
                        element[0].focus();
                    });
                }
            });
        }
    };
});