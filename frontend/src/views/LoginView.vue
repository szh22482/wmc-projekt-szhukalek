<template class="body">
  <v-container class="login-container">
    <v-card class="login-input">
      <v-card-title id="header">Login</v-card-title>
      <v-card-text>
      <div class="email-input">
        <v-text-field v-model="email" label="Email" placeholder=" " type="email" variant="solo-filled"></v-text-field>
      </div>
      <div class="password-input">
        <v-text-field v-model="password" label="Password" type="password" variant="solo-filled"></v-text-field>
      </div>
      </v-card-text>
      <v-card-actions>
        <div class="submit-button">
          <v-btn @click="login" variant="tonal">Submit</v-btn>
        </div>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script setup>
  import axios from "axios";
</script>

<script>
import axios from "axios";

export default {
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    async login() {
      try {
        const response = await axios.get('/users/login', {
          params: {
            email: this.email,
            password: this.password
          }
        })
        if(response.status === 200) {
          console.log("Login successful!")
        } else {
          console.log("Login unsuccessgful")
        }
      } catch (e) {
        console.log(e);
      }
    }
  }
}

</script>

<style scoped>
#header {
  padding-bottom: 5px;
}
.login-input {
  display: block;
  background-color: white;
  border-radius: 14px;
  box-shadow: 0 3px 10px rgb(0 0 0 / 0.2);
  border: 1px solid lightgray;
  width: 600px;
}
.login-container{
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

#header {
  font-size: 28px;
  margin-bottom: 20px;
}

.email-input {

}

.password-input {
}
</style>
