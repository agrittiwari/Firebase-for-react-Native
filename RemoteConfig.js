import React, { useEffect } from 'react';
import remoteConfig from '@react-native-firebase/remote-config';

const RemoteConfig=() => {
  
    remoteConfig()
      .setDefaults({
        awesome_new_feature: 'disabled',
      })
      .then(() => {
        console.log('Default values set.');
      });
  
}


export default RemoteConfig;