import splashscreen from '../screens/splashscreen
import medicinescreen from '../screens/medicinescreen
import  authscreen from '../screens/authscreen
import  chatscreen from '../screens/chatscreen
import  homescreen from '../screens/homescreen
import insightscreen from '../screens/insightscreen
import notification from '../screens/notification
import onboardingscreen from '../screens/onboardingscreen
import settingscreen from '../screens/settingscreen
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