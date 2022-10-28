import { View, Text, TouchableOpacity } from 'react-native'
import React from 'react'

import styles from './style'
import { viewColorS2, buttonTextS2 } from '../../RemoteConfigValues'


const Screen2 = ({navigation}) => {
  return (
    <View>
      <Text>Screen2</Text>

      <View style={[styles.goodView, {backgroundColor:viewColorS2}]}></View>
      <TouchableOpacity
        style={styles.button}
        onPress={()=>navigation.goBack()}
      >
        <Text style={{marginHorizontal:2, fontSize:20, color: 'black'}}>{buttonTextS2}</Text>
      </TouchableOpacity>
    </View>
  );
}

export default Screen2