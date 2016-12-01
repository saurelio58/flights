'use strict';

(() => {
  angular
    .module('flights')
    .run(run);

  run.$inject = ['accessService', '$rootScope', '$state', '$stateParams'];

  function run(accessService, $rootScope, $state, $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;

    $rootScope.$on('$stateChangeStart', function(event, toState, toStateParams) {
      let loggedIn = accessService.isLoggedIn();

      if (toState.data == false && !loggedIn) {
        event.preventDefault();
        $state.go('login')
      }

    });
  }

})();
