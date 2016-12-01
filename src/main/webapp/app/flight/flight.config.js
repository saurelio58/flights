'use strict';

(() => {
  angular
    .module('flights.flight')
    .config(config)

  config.$inject = ['flightRoutes', '$stateProvider']

  function config(flightRoutes, $stateProvider) {
    Object.keys(flightRoutes) // JS built in function
      .forEach(key => {
        $stateProvider
          .state(key, flightRoutes[key]); //accesses each state object given the key and the object
      })
  }

})();
