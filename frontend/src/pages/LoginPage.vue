<!-- src/pages/LoginPage.vue -->
<template>
  <q-page class="row items-center justify-evenly">
    <!-- Design template by Joabson Arley
  https://github.com/Joabsonlg/quasar-template-->

    <q-card class="q-ma-xl">
      <div class="row">
        <div class="col-0 col-sm-5 bg-primary rounded-left-borders xs-hide">
          <div
            class="row full-width q-px-xl q-pb-xl full-height flex flex-center"
          >
            <div class="">
              <div
                class="text-h4 text-uppercase text-white fredoka"
                style="min-width: 220px"
              >
                স্বাগত!
              </div>
              <div class="text-white q-my-sm text-subtitle1">
                গরুর মাংস অর্ডার করতে সাইন ইন করুন!
              </div>
            </div>
          </div>
        </div>

        <div class="col-12 col-sm-7">
          <div class="row q-pa-sm-sm q-pa-md">
            <div class="col-12">
              <q-card-section>
                <div class="q-mb-xl">
                  <div class="flex justify-center">
                    <div
                      class="text-h4 text-uppercase q-my-none text-weight-bold text-primary fredoka"
                    >
                      লগইন করুন
                    </div>
                  </div>
                </div>

                <q-form ref="form" class="q-gutter-md" @submit="submit">
                  <q-input v-model="user.email" label="ইমেইল" name="Email" />

                  <q-input
                    v-model="user.password"
                    label="পাসওয়ার্ড"
                    name="password"
                    type="password"
                  />

                  <div>
                    <q-btn
                      class="full-width fredoka"
                      color="primary"
                      label="লগইন"
                      rounded
                      type="submit"
                    ></q-btn>

                    <div class="q-mt-lg">
                      <div class="q-mt-sm">
                        এখনো কোন একাউন্ট নেই?
                        <router-link class="text-primary" to="/auth/register"
                          >এখানে নিবন্ধন করুন</router-link
                        >
                      </div>
                    </div>
                  </div>
                </q-form>
              </q-card-section>
            </div>
          </div>
        </div>
      </div>
    </q-card>
  </q-page>
</template>

<script lang="ts">
import { defineComponent, ref, reactive } from 'vue';
import { useRouter, useRoute } from 'vue-router';

import { useQuasar } from 'quasar';
import { axios } from 'boot/axios';
//import { ref, reactive } from 'vue';

import { useUserStore } from 'stores/userstore';
import { storeToRefs } from 'pinia';

export default defineComponent({
  name: 'LoginPage',

  setup() {
    const $q = useQuasar();

    const store = useUserStore();
    store.setId(null);

    const router = useRouter();
    const route = useRoute();
    const user = reactive({
      email: 'ismail.fakir365@gmail.com',
      password: 'abc123',
    });

    const form = ref(null);

    function loginUser(user) {
      axios
        .post('/api/users/login', {
          email: user.email,
          password: user.password,
        })
        .then((response) => {
          console.log(response);
          $q.notify({
            color: 'green-4',
            textColor: 'white',
            icon: 'fact_check',
            message: 'লগইন সফল হয়েছে',
          });
          store.setId(response.data);
          router.push('/beeforder');
        })
        .catch((error) => {
          console.log('failed to save data in backend' + error);
          $q.notify({
            color: 'negative',
            position: 'top',
            message: 'লগইন ব্যর্থ হয়েছে!',
            icon: 'report_problem',
          });
        });
    }

    const submit = async () => {
      if (form.value.validate()) {
        loginUser(user);
      }
    };
    return {
      store,
      user,
      form,
      submit,
    };
  },
});
</script>
