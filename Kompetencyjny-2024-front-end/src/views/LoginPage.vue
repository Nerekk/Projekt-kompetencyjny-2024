<template>
    <v-main>
    <v-container fluid class="main-container d-flex flex-column bg-dark">

        <v-row no-gutters class="d-flex justify-space-between">
            <v-col cols="12" md="5" class="text-left order-md-1 order-2 main-text-col" >
                <h1 class="main-text"><span class="text-gold">ZADBAJ</span> O
                <br> SWOJĄ 
                <br> PRZYSZŁOŚĆ 
                <br>Z <span class="text-gold">NAMI</span></h1>
            </v-col>
            <v-col cols="12" md="3" class="logo-column order-md-2 order-1" >
                <v-img class="logo-img" src="@/assets/LogoLightGold.png" aspect-ratio="1/1"></v-img>
            </v-col>
        </v-row>

        <v-row no-gutters class="d-flex justify-space-between">

            <v-col cols="12" md="5" class="d-flex flex-column align-center justify-center pb-5 ">
                <v-row>
                    <v-img class="img-img" src="@/assets/image.png" aspect-ratio="1/1"></v-img>
                </v-row>
                <v-row>
                    <v-card
                    class=" mx-auto my-8 info-card "
                    variant="text"
                    style="color: #FFFEED;"
                  >
                    <v-card-item>
                      <v-card-title class="card-title">
                        Dlaczego my
                      </v-card-title>
                    </v-card-item>
                
                    <v-card-text class="d-flex flex-row justify-space-between card">
                        <div class="card-element">
                            <p class="big-card-element">80%</p>
                            <p class="small-card-element">SKUTECZNOŚCI</p>
                        </div>
                        <div class="card-element">
                            <p class="big-card-element">3</p>
                            <p class="small-card-element">RÓŻNE MODELE DOSTOSOWANE DO TWOICH POTRZEB</p>
                        </div>
                        <div class="card-element">
                            <p class="big-card-element">2K+</p>
                            <p class="small-card-element">ZADOWOLONYCH UŻYTKOWNIKÓW</p>     
                        </div>               
                    </v-card-text>
                  </v-card>
                </v-row>
            </v-col>

            <v-col cols="12" md="7" class="d-flex justify-end login-column">
                <v-card class="elevation-0 login-card pt-10" v-if="!registerForm">
                    <v-card-title style="font-size: 40px; font-weight:800; line-height: 4rem;">
                        LOGOWANIE
                    </v-card-title>
                    <v-form class="login-form" @submit.prevent="handleLogin" ref="loginForm" v-model="loginValid">
                        <v-card-text style="width: 100%; display: flex; flex-direction: column; align-items: center;">
                            <v-text-field label="Email" v-model="loginUser.email" :rules="emailRules" style="width: 80%;"></v-text-field>
                            <v-text-field label="Hasło" v-model="loginUser.password" :rules="passwordRule" type="password" style="width: 80%;"></v-text-field>
                        </v-card-text>
                        <v-card-actions style="display: flex; flex-direction: column; align-items: center;">
                            <v-btn class="bg-dark text-gold pa-7 d-flex align-center justify-center" style="border-radius: 30px; text-align:center; font-size: 18px" :disabled="!loginValid" type="submit">
                                Zaloguj się
                            </v-btn>
                            <div class="text-h6 font-weight-bold text-dark text-center mt-6">
                                Nie masz jeszcze konta?
                            </div>
                            <v-btn color="dark mt-3" variant="outlined" @click="registerForm = !registerForm">Zarejestruj się</v-btn>
                        </v-card-actions>
                    </v-form>
                </v-card>

                <v-card class="elevation-0 login-card pt-10" v-else>
                    <v-card-title style="font-size: 40px; font-weight:800; line-height: 4rem;">
                        REJESTRACJA
                    </v-card-title>
                    <v-form class="login-form" @submit.prevent="handleRegister" ref="registerForm" v-model="registerValid">
                        <v-card-text style="width: 100%; display: flex; flex-direction: column; align-items: center;">
                            <v-text-field label="Email" v-model="registerUser.email" :rules="emailRules" style="width: 80%;"></v-text-field>
                            <v-text-field label="Hasło" v-model="registerUser.password" :rules="passwordRule" type="password" style="width: 80%;"></v-text-field>
                            <v-text-field label="Powtórz hasło" v-model="registerUser.confirmPassword" :rules="passwordMatchRule" type="password" style="width: 80%;"></v-text-field>
                        </v-card-text>
                        <v-card-actions style="display: flex; flex-direction: column; align-items: center;">
                            <v-btn class="bg-dark text-gold pa-7 d-flex align-center justify-center" style="border-radius: 30px; text-align:center; font-size: 18px" :disabled="!registerValid" type="submit">
                                Zarejestruj się
                            </v-btn>
                            <div class="text-h6 font-weight-bold text-dark text-center mt-2">
                                Masz już konto?
                                </div>
                                <v-btn color="dark mt-2" variant="outlined" @click="registerForm = !registerForm">Zaloguj
                                się</v-btn>
                        </v-card-actions>
                    </v-form>
                </v-card>

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
    .test{
        background-color:red;
    }

    .test2{
        background-color:blue;
    }

    .main-container{
        padding: 30px 30px 30px 30px;
    }

    h1{
        font-size: 90px;
        line-height: 1.2;
        color: #FFFEED;
    }

    @media (max-width: 960px) {
        .main-text-col{
            text-align: center !important;
        }
    }

    .logo-img, .img-img{
        width: 250px;
    }

    .logo-column {
        display: flex;
        flex-direction: column !important;
        align-items: center;
        justify-content: flex-start;
        
    }

    .card{
        gap: 50px;
    }

    .card-title{
        font-size: 40px; 
        font-weight:800; 
        line-height: 4rem;
    }

    .card-element{
        display: flex;
        flex-direction: column !important;
        align-items: center;
        width: min-content;
        text-align: center;
    }

    .big-card-element{
        font-size: 80px;
        font-weight: 800;
        line-height: 5rem;
    }

    .small-card-element{
        font-size: 20px;
    }

    .info-card::after{
        content: "";
        position: absolute;
        width: 200px;
        height: 100%;
        border-top: 4px solid rgb(var(--v-theme-gold));
        border-right: 4px solid rgb(var(--v-theme-gold));
        top: 40px;
        right: 0px;
        z-index: -1;
    }

    .info-card::before{
        content: "";
        position: absolute;
        background-color: #FFFEED;
        width: 30px;
        height: 30px;
        border-radius: 100%;
        top: 28px;
        right: 200px;
        z-index: 100;
    }

    .login-column{
        background-image: url(@/assets/LoginBackground.png);
    }

    .login-card{
        display: flex;
        flex-direction: column;
        align-items: center;
        background-color: rgba(255, 254, 237, 0.7); 
        width: 50%;
        gap: 10px;
    }

    .login-form{
        width: 80%;
    }

    .login-btn{
        border-radius: 70%;
    }

    @media (max-width: 600px) {
        h1{
            font-size: 55px;
            line-height: 1.2;
        }

        .logo-img{
            width: 150px;
            margin-bottom: 40px;
        }

        .img-img{
            width: 200px;
            margin-top: 40px;
        }

        .card{
            gap: 10px;
        }

        .card-title{
            font-size: 30px; 
            font-weight:800; 
            line-height: 4rem;
        }

        .big-card-element{
            font-size: 40px;
            font-weight: 800;
            line-height: 5rem;
        }

        .small-card-element{
            font-size: 13px;
        }

        .info-card::after{
            width: 100px;
        }
    
        .info-card::before{
            width: 15px;
            height: 15px;
            top: 35px;
            right: 99px;
        }

        .login-card{
            width: 100%;
        }
    }
</style>
  