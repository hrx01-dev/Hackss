Import splashscreen from '../screens/splashscreen
Import medicinescreen from '../screens/medicinescreen
Import  from authscreen from '../screens/authscreen
Import  from chatscreen '../screens/chatscreen
Import  from homescreen '../screens/homescreen
Import insightscreen from '../screens/insightscreen
Import notification from '../screens/notification
Import onboardingscreen from '../screens/onboardingscreen
Import settingscreen from '../screens/settingscreen
const Stack = createStackNavigator();

export default function AppNavigator() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Splash">

        <Stack.Screen
          name="Splash"
          component={SplashScreen}
          options={{ headerShown: false }}
        />

        <Stack.Screen
          name="Onboarding"
          component={OnboardingScreen}
          options={{ headerShown: false }}
        />

        <Stack.Screen
          name="Auth"
          component={AuthScreen}
          options={{ headerShown: false }}
        />

        <Stack.Screen
          name="Home"
          component={HomeScreen}
          options={{ headerShown: false }}
        />

        <Stack.Screen
          name="Medicine"
          component={MedicineScreen}
          options={{ headerShown: false }}
        />

        <Stack.Screen
          name="Insight"
          component={InsightScreen}
          options={{ headerShown: false }}
        />

        <Stack.Screen
          name="Notification"
          component={NotificationScreen}
          options={{ headerShown: false }}
        />

        <Stack.Screen
          name="Chat"
          component={ChatScreen}
          options={{ headerShown: false }}
        />

        <Stack.Screen
          name="Settings"
          component={SettingScreen}
          options={{ headerShown: false }}
        />

      </Stack.Navigator>
    </NavigationContainer>
  );