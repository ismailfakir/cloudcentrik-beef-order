<template>
  <div class="q-pa-md">
    <div class="row">
      <div class="col-4">
        <q-card>
          <q-card-section>
            <div class="text-h6">এখানে অর্ডার করুন</div>
          </q-card-section>
          <q-card-section>
            <q-form
              @submit="onSubmit"
              @reset="onReset"
              class="q-gutter-md"
              ref="orderForm"
            >
              <q-input
                filled
                v-model="name"
                label="আপনার নাম *"
                hint="আপনার পুরো নাম"
                lazy-rules
                :rules="[
                  (val) =>
                    (val && val.length > 0) || 'আপনার পুরো নাম টাইপ করুন',
                ]"
              />

              <q-input
                filled
                v-model="mobile"
                label="আপনার মোবাইল নম্বর *"
                lazy-rules
                :rules="[
                  (val) =>
                    (val && val.length > 0) || 'আপনার মোবাইল নম্বর টাইপ করুন',
                ]"
              />

              <q-input
                filled
                type="number"
                v-model="orderPortion"
                label="গরুর মাংসের অর্ডারের অংশ *"
                step="0.5"
                lazy-rules
                :rules="[
                  (val) =>
                    (val !== null && val !== '') ||
                    'গরুর মাংস আপনার অংশ নির্বাচন করুন',
                  (val) =>
                    (val > 0 && val < 3) ||
                    'অনুগ্রহ করে 1 এবং 3 এর মধ্যে অংশ নির্বাচন করুন',
                ]"
              />

              <q-toggle
                v-model="accept"
                label="আমি স্বীকার করি যে আমি অবশ্যই আমার গরুর মাংসের অর্ডার পরিবর্তন করব না"
              />

              <div>
                <q-btn
                  label="গরুর মাংসের অর্ডার নিশ্চিত করুন"
                  type="submit"
                  color="primary"
                />
                <q-btn
                  label="অর্ডার পুনরায় সেট করুন"
                  type="reset"
                  color="primary"
                  flat
                  class="q-ml-sm"
                />
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </div>
      <div class="col-8">
        <!-- <q-card>
          <q-card-section> -->
        <q-table
          style="height: 600px"
          title="গরুর মাংসের অর্ডারের তালিকা"
          :rows="orders"
          :columns="columns"
          row-key="index"
          no-data-label="এখনো কোনো গরুর মাংসের অর্ডার জমা দেওয়া হয়নি"
          virtual-scroll
          :rows-per-page-options="[0]"
          dense
        >
          <template v-slot:body-cell-done="props">
            <q-td :props="props">
              <div>
                <q-chip v-if="props.row.done">
                  <q-avatar icon="done" color="green" text-color="white" />হ্যাঁ
                </q-chip>
                <q-chip v-else>
                  <q-avatar icon="clear" color="red" text-color="white" />না
                </q-chip>
              </div>
            </q-td>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props">
              <div>
                <q-btn
                  icon="delete"
                  dense
                  round
                  flat
                  size="md"
                  color="red"
                  @click="showDeleteOrderDialog(props.row)"
                ></q-btn>
                <q-btn
                  icon="edit"
                  dense
                  round
                  flat
                  size="md"
                  color="orange"
                  @click="showEditOrderDialog(props.row)"
                ></q-btn>
              </div>
            </q-td>
          </template>
        </q-table>
        <!-- </q-card-section>
        </q-card> -->
      </div>
      <!--Edit order start-->
      <q-dialog v-model="showEditOrder" persistent>
        <q-card style="width: 600px; max-width: 60vw">
          <q-card-section class="row items-center">
            <q-avatar icon="edit" color="primary" text-color="white" />
            <div class="text-h6">
              <span class="q-ml-sm"
                >অর্ডার আপডেট করুন
                <q-chip outline square color="red" text-color="white" icon="tag"
                  >অর্ডার নম্বর: {{ editOrderData.orderNumber }}</q-chip
                ></span
              >
            </div>
          </q-card-section>
          <q-separator inset></q-separator>
          <q-card-section class="q-pt-none">
            <q-form class="q-gutter-md">
              <q-list>
                <q-item>
                  <q-item-section>
                    <q-input
                      filled
                      v-model="editOrderData.name"
                      label="আপনার নাম *"
                      lazy-rules
                      :rules="[
                        (val) =>
                          (val && val.length > 0) || 'আপনার পুরো নাম টাইপ করুন',
                      ]"
                    />

                    <q-input
                      filled
                      v-model="editOrderData.mobile"
                      label="আপনার মোবাইল নম্বর *"
                      lazy-rules
                      :rules="[
                        (val) =>
                          (val && val.length > 0) ||
                          'আপনার মোবাইল নম্বর টাইপ করুন',
                      ]"
                    />
                    <q-input
                      filled
                      type="number"
                      v-model="editOrderData.orderPortion"
                      label="গরুর মাংসের অর্ডারের অংশ *"
                      step="0.5"
                      lazy-rules
                      :rules="[
                        (val) =>
                          (val !== null && val !== '') ||
                          'গরুর মাংস আপনার অংশ নির্বাচন করুন',
                        (val) =>
                          (val > 0 && val < 3) ||
                          'অনুগ্রহ করে 1 এবং 3 এর মধ্যে অংশ নির্বাচন করুন',
                      ]"
                    />
                    <q-toggle
                      v-model="editOrderData.done"
                      color="green"
                      label="অর্ডার সম্পন্ন হয়েছে"
                      left-label
                    />
                  </q-item-section>
                </q-item>
              </q-list>
            </q-form>
          </q-card-section>
          <q-card-section>
            <q-card-actions align="right">
              <q-btn
                flat
                label="এটি বন্ধ করুন"
                color="secondary"
                v-close-popup
                @click="clearEditOrderData()"
              ></q-btn>
              <q-btn
                flat
                label="অর্ডার সংরক্ষণ করুন"
                color="primary"
                v-close-popup
                @click="confirmEditOrder()"
              ></q-btn>
            </q-card-actions>
          </q-card-section>
        </q-card>
      </q-dialog>
      <!--Edit order end-->
      <!--Delete order start-->
      <q-dialog v-model="showDeleteOrder" persistent>
        <q-card>
          <q-card-section class="row items-center">
            <q-avatar icon="delete" color="primary" text-color="white" />
            <span class="q-ml-sm"
              >আপনি কি নিশ্চিত যে নিচের নামের অর্ডারটি মুছে ফেলতে চান?</span
            >
            <q-separator inset></q-separator>
            <q-list>
              <q-item
                ><q-chip
                  outline
                  square
                  color="red"
                  text-color="white"
                  icon="tag"
                  >অর্ডার নম্বর: {{ editOrderData.orderNumber }}</q-chip
                >
                <q-chip
                  outline
                  square
                  color="primary"
                  text-color="white"
                  icon="person"
                  >নাম: {{ editOrderData.name }}</q-chip
                ></q-item
              >
            </q-list>
          </q-card-section>

          <q-card-actions align="right">
            <q-btn
              flat
              label="এটি বন্ধ করুন"
              color="secondary"
              v-close-popup
              @click="clearEditOrderData()"
            />
            <q-btn
              flat
              label="এই অর্ডার বাতিল করুন"
              color="primary"
              v-close-popup
              @click="confirmDeleteOrder(editOrderData.orderNumber)"
            />
          </q-card-actions>
        </q-card>
      </q-dialog>
      <!--Delete order end-->
    </div>
  </div>
</template>

<script>
import { useQuasar } from 'quasar';
import { axios } from 'boot/axios';
import { ref, onBeforeUnmount } from 'vue';

export default {
  setup() {
    const $q = useQuasar();
    //const banglaNumber = '০,১,২,৩,৪,৫,৬,৭,৮,৯';
    const orderForm = ref(null);

    const actions = ref('');
    const orderNumber = ref(0);
    const name = ref(null);
    const mobile = ref(null);
    const orderPortion = ref(null);
    const done = ref(false);
    const accept = ref(false);
    const orders = ref([]);
    const selected = ref([]);

    const showEditOrder = ref(false);
    const showDeleteOrder = ref(false);
    const editOrderData = ref({});

    const data = ref(null);

    const columns = [
      {
        name: 'no',
        align: 'center',
        label: 'ক্রমিক নং',
        field: (row) => row.no,
        sortable: true,
      },

      {
        name: 'name',
        align: 'center',
        label: 'নাম',
        field: 'name',
        sortable: true,
      },
      {
        name: 'mobile',
        align: 'center',
        label: 'মোবাইল',
        field: 'mobile',
      },
      {
        name: 'orderPortion',
        align: 'center',
        label: 'ভাঘ',
        field: 'orderPortion',
      },
      {
        name: 'done',
        align: 'center',
        label: 'অর্ডার সম্পন্ন হয়েছে?',
        field: 'done',
      },
      {
        name: 'orderDate',
        align: 'center',
        label: 'শেষ আপডেটের তারিখ',
        field: 'orderDate',
      },
      {
        name: 'orderNumber',
        align: 'center',
        label: 'অর্ডার নম্বর',
        field: (row) => row.orderNumber,
        sortable: true,
      },
      {
        name: 'actions',
        align: 'center',
        label: 'অ্যাকশন',
        field: '',
      },
    ];

    let timer;

    onBeforeUnmount(() => {
      if (timer !== void 0) {
        clearTimeout(timer);
        $q.loading.hide();
      }
    });

    function loadData() {
      $q.loading.show({
        message: 'অনুগ্রহ করে ডাটাবেসে ডেটা সিঙ্ক করার জন্য অপেক্ষা করুন',
      });
      console.log('calling backend api');
      axios
        .get('/api/orders')
        .then((response) => {
          data.value = response.data;
          console.log(data.value._embedded.orders);
          orders.value = [];
          data.value._embedded.orders.forEach((el, index) => {
            let orderNumber = el._links.order.href.split('/orders/')[1];
            let no = index + 1;
            orders.value.push({
              no: no,
              orderNumber: orderNumber,
              name: el.name,
              mobile: el.mobile,
              orderPortion: el.amount,
              done: el.done,
              orderDate: el.orderedAt,
              actions: '',
            });
          });
        })
        .catch((error) => {
          console.log('failed loding data from backend' + error);
          $q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading failed',
            icon: 'report_problem',
          });
        });
      // hiding in 2s
      timer = setTimeout(() => {
        $q.loading.hide();
        timer = void 0;
      }, 2000);
    }

    function saveOrder(order) {
      console.log(JSON.stringify(order));
      let dateNow = new Date().toJSON();
      axios
        .post('/api/orders', {
          name: order.name,
          mobile: order.mobile,
          amount: order.orderPortion,
          done: order.done,
          orderedAt: dateNow,
        })
        .then((response) => {
          console.log(response);
          $q.notify({
            color: 'green-4',
            textColor: 'white',
            icon: 'fact_check',
            message: 'গরুর মাংসের অর্ডার তালিকায় যোগ করা হয়েছে',
          });
          loadData();
        })
        .catch((error) => {
          console.log('failed to save data in backend' + error);
          $q.notify({
            color: 'negative',
            position: 'top',
            message: 'Failed saving order!',
            icon: 'report_problem',
          });
        });
    }

    function resetOrderForm() {
      name.value = null;
      (mobile.value = null), (orderPortion.value = null);
      accept.value = false;
      orderForm.value.resetValidation();
    }

    function updateOrder(order) {
      console.log(JSON.stringify(order));
      let dateNow = new Date().toJSON();
      let orderNo = order.orderNumber;
      axios
        .put('/api/orders/' + orderNo, {
          name: order.name,
          mobile: order.mobile,
          amount: order.orderPortion,
          done: order.done,
          orderedAt: dateNow,
        })
        .then((response) => {
          console.log(response);
          $q.notify({
            color: 'green-4',
            textColor: 'white',
            icon: 'fact_check',
            message: 'গরুর মাংসের অর্ডার আপডেট করা হয়েছে',
          });
          loadData();
        })
        .catch((error) => {
          console.log('failed to save data in backend' + error);
          $q.notify({
            color: 'negative',
            position: 'top',
            message: 'অর্ডার আপডেট করতে ব্যর্থ হয়েছে, কিছু ভুল হয়েছে!',
            icon: 'report_problem',
          });
        });
    }

    function deleteOrder(orderNo) {
      axios
        .delete('/api/orders/' + orderNo)
        .then((response) => {
          console.log(response);
          $q.notify({
            color: 'green-4',
            textColor: 'white',
            icon: 'fact_check',
            message: 'গরুর মাংসের অর্ডার তালিকা থেকে মুছে ফেলা হয়েছে',
          });
          loadData();
        })
        .catch((error) => {
          console.log('failed to delete data in backend' + error);
          $q.notify({
            color: 'negative',
            position: 'top',
            message: 'ডিলিট অর্ডার ব্যর্থ হয়েছে, কিছু ভুল হয়েছে!',
            icon: 'report_problem',
          });
        });
    }

    // load data on start
    loadData();

    return {
      orderForm,
      loadData,
      data,
      actions,
      name,
      mobile,
      orderPortion,
      done,
      accept,
      orders,
      selected,
      columns,
      showEditOrder,
      editOrderData,
      showDeleteOrder,
      saveOrder,
      updateOrder,
      deleteOrder,

      onSubmit() {
        const index = orders.value.length + 1;
        const dateObject = new Date();
        let orderDateNow = dateObject.toISOString();
        if (accept.value !== true) {
          $q.notify({
            color: 'red-5',
            textColor: 'white',
            icon: 'warning',
            message: 'You need to accept the license and terms first',
          });
        } else {
          saveOrder({
            name: name.value,
            mobile: mobile.value,
            orderPortion: orderPortion.value,
            done: false,
          });
          resetOrderForm();

          /* orders.value.push({
            orderNumber: index,
            name: name.value,
            mobile: mobile.value,
            orderPortion: orderPortion.value,
            done: false,
            orderDate: orderDateNow,
            actions: actions.value,
          }); */
        }
      },

      showEditOrderDialog(order) {
        editOrderData.value = {
          orderNumber: order.orderNumber,
          name: order.name,
          mobile: order.mobile,
          orderPortion: order.orderPortion,
          done: order.done,
          orderDate: order.orderDate,
        };
        showEditOrder.value = true;
      },
      confirmEditOrder() {
        /* const ordersCopy = [...orders.value];
        const targetIndex = orders.value.findIndex(
          (f) => f.orderNumber === editOrderData.value.orderNumber
        );
        ordersCopy[targetIndex] = editOrderData.value;

        editOrderData.value = {};
        orders.value = ordersCopy;
        $q.notify({
          color: 'green-4',
          textColor: 'white',
          icon: 'fact_check',
          message: 'গরুর মাংসের অর্ডার আপডেট করা হয়েছে',
        }); */

        updateOrder({
          orderNumber: editOrderData.value.orderNumber,
          name: editOrderData.value.name,
          mobile: editOrderData.value.mobile,
          orderPortion: editOrderData.value.orderPortion,
          done: editOrderData.value.done,
          orderDate: editOrderData.value.orderDate,
        });
      },
      showDeleteOrderDialog(order) {
        editOrderData.value = {
          name: order.name,
          orderNumber: order.orderNumber,
          mobile: order.mobile,
          orderPortion: order.orderPortion,
          orderDate: order.orderDate,
        };

        showDeleteOrder.value = true;
      },
      confirmDeleteOrder(orderNumber) {
        /* orders.value = orders.value.filter(
          (x) => x.orderNumber !== orderNumber
        );
        editOrderData.value = {};
        $q.notify({
          color: 'green-4',
          textColor: 'white',
          icon: 'fact_check',
          message: 'গরুর মাংসের অর্ডার তালিকা থেকে মুছে ফেলা হয়েছে',
        }); */
        deleteOrder(orderNumber);
      },
      clearEditOrderData() {
        editOrderData.value = {};
      },

      onReset() {
        name.value = null;
        (mobile.value = null), (orderPortion.value = null);
        accept.value = false;
      },
    };
  },
};
</script>
