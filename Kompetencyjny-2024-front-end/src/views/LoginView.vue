<template>
  <v-main class="background">
    <v-container fluid>
      <v-row no-gutters>
        <v-col cols="12" md="6">
          <v-row class="mt-8 ml-12">
            <v-col cols="12">
              <div class="text-h1 font-weight-bold">
                <div><span class="text-gold">ZADBAJ</span> O </div>
                <div>SWOJĄ</div>
                <div>PRZYSZŁOŚĆ</div>
                <div>Z <span class="text-gold">NAMI</span></div>
              </div>
            </v-col>
            <v-col cols="12">
              <div class="text-h5 my-8" color="primary" dark>Dlaczego my</div>
              <v-row class="pr-12 mr-12">
                <v-col cols="3">
                  <div class="text-h4 text-center font-weight-bold">80%</div>
                  <div class="text-h6 text-center">SKUTECZNOŚCI</div>
                </v-col>
                <v-col cols="3">
                  <div class="text-h4 text-center font-weight-bold">3</div>
                  <div class="text-h6 text-center">
                    RÓŻNE MODELE DOSTOSOWANE DO TWOICH POTRZEB
                  </div>
                </v-col>
                <v-col cols="3">
                  <div class="text-h4 text-center font-weight-bold">2K+</div>
                  <div class="text-h6 text-center">
                    ZADOWOLONYCH UŻYTKOWNIKÓW
                  </div>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12" md="6">
          <v-row class="mt-3 ml-12">
            <v-col cols="12">
              <v-img class="ml-auto mr-16" :width="150" src="@/assets/LogoLightGold.png" aspect-ratio="1/1"></v-img>
            </v-col>
            <v-col cols="12" class="relative">
              <v-img class="login-background" src="@/assets/LoginBackground.png" aspect-ratio="16/9"></v-img>
              <div class="login-form-container" v-if="!registerForm">
                <v-form @submit.prevent="handleLogin" ref="loginForm" v-model="loginValid">
                  <div class="text-h3 font-weight-bold text-dark text-center mb-6">
                    LOGOWANIE
                  </div>
                  <v-text-field v-model="loginUser.email" class="text-black mx-12" label="Email" outlined dense
                    single-line :rules="emailRules"></v-text-field>
                  <v-text-field v-model="loginUser.password" class="text-black mx-12" label="Hasło" type="password"
                    outlined dense single-line :rules="passwordRule"></v-text-field>
                  <div class="text-center">
                    <v-btn type="submit" color="dark" :disabled="!loginValid">Zaloguj się</v-btn>
                    <div class="text-h6 font-weight-bold text-dark text-center mt-6">
                      Nie masz jeszcze konta?
                    </div>
                    <v-btn color="dark mt-3" variant="outlined" @click="registerForm = !registerForm">Zarejestruj
                      się</v-btn>
                  </div>
                </v-form>
              </div>
              <div class="login-form-container" v-else>
                <v-form @submit.prevent="handleRegister" ref="registerForm" v-model="registerValid">
                  <div class="text-h3 font-weight-bold text-dark text-center mb-6">
                    REJESTRACJA
                  </div>
                  <v-text-field v-model="registerUser.email" class="text-black mx-12" label="Email" outlined dense
                    single-line :rules="emailRules"></v-text-field>
                  <v-text-field v-model="registerUser.password" class="text-black mx-12" label="Hasło" type="password"
                    outlined dense single-line :rules="passwordRule"></v-text-field>
                  <v-text-field v-model="registerUser.confirmPassword" class="text-black mx-12" label="Powtórz hasło"
                    type="password" outlined dense single-line :rules="passwordMatchRule"></v-text-field>
                  <div class="text-center">
                    <v-btn type="submit" color="dark" :disabled="!registerValid">Zarejestruj się</v-btn>
                    <div class="text-h6 font-weight-bold text-dark text-center mt-2">
                      Masz już konto?
                    </div>
                    <v-btn color="dark mt-2" variant="outlined" @click="registerForm = !registerForm">Zaloguj
                      się</v-btn>
                  </div>
                </v-form>
              </div>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script setup>
import { ref, reactive } from "vue";
import { useAuthStore } from "@/store/AuthStore";
import { useRouter } from "vue-router";
import { useSnack } from '@/composables/useSnack';

const { snackbarSuccess, snackbarError } = useSnack();
const authStore = useAuthStore();
const router = useRouter();

const registerForm = ref(false);

const loginValid = ref(false);
const registerValid = ref(false);


const loginUser = reactive({
  email: '',
  password: '',
});
const registerUser = reactive({
  email: '',
  password: '',
  confirmPassword: ''
});
const emailRules = [
  v => !!v || 'Email jest wymagany',
  v => /.+@.+\..+/.test(v) || 'Email musi być prawidłowy'
];
const passwordRule = [
  v => !!v || 'Hasło jest wymagane'
]
const passwordMatchRule = [
  () => registerUser.password === registerUser.confirmPassword || 'Hasła nie są takie same',
  v => !!v || 'Powtórz hasło'
];

const handleLogin = async () => {
  await authStore.loginUser(loginUser)
    .then(() => {
      snackbarSuccess('Zalogowano pomyślnie.')
    })
    .catch(() => {
      snackbarError('Nie udało się zalogować. Sprawdź dane i spróbuj ponownie.')
    })
    .finally(() => {
      if (authStore.user)
        router.push('/');
    });
};
const handleRegister = async () => {
  if (registerValid.value) {
    const userFormatted = {
      email: registerUser.email,
      password: registerUser.password
    };
    await authStore.registerUser(userFormatted)
      .then(() => {
        snackbarSuccess('Rejestracja zakończona pomyślnie!')
      })
      .catch(() => {
        snackbarError('Rejestracja nie powiodła się. Być może istnieje użytkownik o podanym adresie email.')
      })
      .finally(() => {
        if (authStore.user)
          router.push('/');
      });
  }
}
</script>

<style scoped>
.background {
  background-color: #2d2415;
  min-height: 100vh;
  color: #ffffff;
}

[class^="text-"] {
  font-family: poppins;
}

.login-background {
  position: absolute;
  bottom: 0;
  right: 0;
  width: 58%;
  height: 70%;
  margin-right: 1%;
  z-index: 0;
}

.login-form-container {
  position: absolute;
  top: 40%;
  right: 5%;
  width: 50%;
  height: 50%;
  padding: 20px;
  opacity: 0.9;
  background-color: #fffeed;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}
</style>
