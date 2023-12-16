<!-- src/pages/RegisterPage.vue -->

<template>
  <!-- Template by Joabson Arley
  https://github.com/Joabsonlg/quasar-template -->

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
              স্বাগত!!
            </div>
            <div class="text-white q-my-sm text-subtitle1">
              গরুর মাংস অর্ডার করার জন্য নিবন্ধন করুন
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
                    রেজিস্ট্রেশন ফর্ম
                  </div>
                </div>
              </div>

              <q-form ref="form" class="q-gutter-md" @submit="submit">
                <q-input
                  v-model="user.name"
                  label="পুরো নাম"
                  name="পুরো নাম"
                  lazy-rules
                  :rules="[
                    (val) =>
                      (val && val.length > 0) || 'আপনার পুরো নাম টাইপ করুন',
                  ]"
                />
                <q-input
                  v-model="user.mobile"
                  label="মোবাইল"
                  name="মোবাইল"
                  lazy-rules
                  :rules="[
                    (val) =>
                      (val && val.length > 0) || 'আপনার মোবাইল নম্বর টাইপ করুন',
                  ]"
                />
                <q-input
                  v-model="user.email"
                  label="ইমেইল"
                  name="ইমেইল"
                  lazy-rules
                  :rules="[
                    (val) => validateEmail(val) || 'একটি বৈধ ইমেল হতে হবে।',
                  ]"
                />
                <q-input
                  v-model="user.password"
                  label="পাসওয়ার্ড"
                  name="পাসওয়ার্ড"
                  type="password"
                  lazy-rules
                  :rules="[
                    (val) =>
                      (val && val.length > 4) ||
                      'পাসওয়ার্ড অন্তত পাঁচ অক্ষরের হতে হবে',
                  ]"
                />

                <div>
                  <q-btn
                    class="full-width fredoka"
                    color="primary"
                    label="নিবন্ধন করুন"
                    rounded
                    type="submit"
                  ></q-btn>

                  <div class="q-mt-lg">
                    <div class="q-mt-sm">
                      ইতিমধ্যে একটি অ্যাকাউন্ট আছে?
                      <router-link class="text-primary" to="/auth/login"
                        >এখানে লগইন করুন</router-link
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
</template>

<script lang="ts">
import { defineComponent, ref, reactive } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useQuasar } from 'quasar';
import { axios } from 'boot/axios';
export default defineComponent({
  name: 'RegisterPage',

  setup() {
    const $q = useQuasar();
    const router = useRouter();
    const route = useRoute();

    const user = reactive({
      name: null,
      mobile: null,
      email: null,
      password: null,
    });

    const form = ref(null);

    function validateEmail(email: string): boolean {
      return /[a-z0-9]+@[a-z]+\.[a-z]{2,3}/.test(email);
    }

    function registerUser(user) {
      axios
        .post('/api/users', {
          name: user.name,
          mobile: user.mobile,
          email: user.email,
          password: user.password,
        })
        .then((response) => {
          console.log(response);
          $q.notify({
            color: 'green-4',
            textColor: 'white',
            icon: 'fact_check',
            message: 'ব্যবহারকারীর নিবন্ধন সফল হয়েছে',
          });
          router.push('/auth/login');
        })
        .catch((error) => {
          console.log('failed to save data in backend' + error);
          $q.notify({
            color: 'negative',
            position: 'top',
            message: 'ব্যবহারকারী নিবন্ধন ব্যর্থ হয়েছে!',
            icon: 'report_problem',
          });
        });
    }

    const submit = async () => {
      if (form.value.validate()) {
        registerUser(user);
      }
    };

    return {
      validateEmail,
      user,
      submit,
      form,
    };
  },
});
</script>
