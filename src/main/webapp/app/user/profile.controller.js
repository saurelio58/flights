'use strict';

(() => {
  angular
    .module('fastbook.user')
    .controller('ProfileController', ProfileController);

  ProfileController.$inject = [
    'user', 'accessService', 'userFriendList', 'userGroupList', 'userService',
    'userPosts', '$scope', '$state', '$stateParams', '$log'
  ];

  function ProfileController(
    user, accessService, userFriendList, userGroupList, userService,
    userPosts, $scope, $state, $stateParams, $log
  ) {
    $log.debug('ProfileController initializing...')

    this.profileUser = user;
    this.loggedInUser = accessService.currentUser;
    this.friendList = userFriendList;
    this.groupList = userGroupList;
    this.postObject = userService.post;
    this.usersPosts = userPosts;

    $log.debug(this.profileUser)
    $log.debug(this.profileUser.id)
    $log.debug(this.loggedInUser)
    $log.debug(this.loggedInUser.id)
    $log.debug(this.friendList)
    $log.debug(this.groupList)

    userService.setProfileUser(user);

    $log.debug(userService.profileUser)

    this.calculateAge = () => {
      var ageDifMs = Date.now() - new Date(this.profileUser.birthDate);
      var ageDate = new Date(ageDifMs);
      return Math.abs(ageDate.getUTCFullYear() - 1970);
    };

    this.post = () => {
      $log.debug('Trying to post')
      $log.debug(this.postObject.text)
      this.postObject.user = this.loggedInUser;
      $log.debug(this.postObject.user)
      $log.debug(this.profileUser)
      $log.debug(this.postObject)
      $scope.date = new Date();
      this.postObject.timestamp = $scope.date;
      $log.debug(this.postObject)
         return userService
            .postToUserTimeline(this.profileUser.id, this.postObject)
            .then($state.go($state.current, {}, {reload: true}));

    }

  }
})();
