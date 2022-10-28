/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';

import { 
  StyleSheet,

  Text,

  useColorScheme,

} from 'react-native';

import {
  Colors,
} from 'react-native/Libraries/NewAppScreen';
import { StackActions } from '@react-navigation/native/lib/typescript/src';
import Screen1 from './Screens/Screen1';
import Screen2 from './Screens/Screen2';

/* $FlowFixMe[missing-local-annot] The type annotation(s) required by Flow's
 * LTI update could not be added via codemod */
import { createStackNavigator } from '@react-navigation/stack';
import RemoteConfig from './RemoteConfig';
const Stack = createStackNavigator()
const App= () => {
useEffect(()=>{
  RemoteConfig()
})


  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName='Screen1'>
         <Stack.Screen name="Screen1" component={Screen1}/>
      <Stack.Screen name="Screen2" component={Screen2}/>
      </Stack.Navigator>
     
    </NavigationContainer>
  );
};

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
