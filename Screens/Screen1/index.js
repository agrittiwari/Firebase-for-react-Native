import { View, Text, TouchableOpacity } from 'react-native'
import React from 'react'
import styles from './style'
import { viewColorS1, buttonTextS1 } from '../../RemoteConfigValues'

const Screen1 = ({navigation}) => {

  // let viewColorS1 = 'red';
  // const buttonTextS1 = 'Press Here'

  return (
    <View>
      
      <View style={[styles.goodView, {backgroundColor:viewColorS1}]}></View>
      <TouchableOpacity
        style={styles.button}
        onPress={()=>navigation.navigate('Screen2')}
      >
        <Text style={{marginHorizontal:2, fontSize:20, color: 'black'}}>{buttonTextS1}</Text>
      </TouchableOpacity>
    </View>
  );
}

export default Screen1