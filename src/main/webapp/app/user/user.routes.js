'use strict';

(() => {
  angular
    .module('flights.user')
    .constant('userRoutes', {
      profile: {
        url: '/users/{id}',
        templateUrl: 'app/user/profile.template.html',
        controller: 'ProfileController',
        controllerAs: '$profileCtrl',
        resolve: {
          user: ['userService', '$stateParams', function(userService, $stateParams) {
            return userService.getUser($stateParams.username);
          }]
        },
        data: {
          loggedIn: true
        },
      }
    })
})();
