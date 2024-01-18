<template>
  <v-card
    flat
    title="Users"
  >
    <template v-slot:text>
      <v-text-field
        v-model="search"
        label="Search"
        prepend-inner-icon="mdi-magnify"
        single-line
        variant="outlined"
        hide-details
      ></v-text-field>
    </template>

    <v-data-table
      :headers="headers"
      :items="users"
      :search="search"
    >
      <template v-slot:item.actions="{ item }">
        <v-icon
          size="small"
          class="me-2"
          @click="openDialog(item)"
        >
          mdi-pencil
        </v-icon>

        <v-icon
          size="small"
          @click="deleteItem(item)"
        >
          mdi-delete
        </v-icon>
      </template>

      <template v-slot:no-data>
        <v-btn
          color="primary"
          @click="initialize"
        >
          Reset
        </v-btn>
      </template>
    </v-data-table>

    <v-dialog v-model="dialog" max-width="600">
      <v-card>
        <v-card-title>
          Benutzer bearbeiten
        </v-card-title>
        <v-card-text>
          <v-form @submit.prevent="saveChanges">
            <v-text-field v-model="editedUser.vorname" label="Vorname"></v-text-field>
            <v-text-field v-model="editedUser.nachname" label="Nachname"></v-text-field>
            <v-text-field v-model="editedUser.email" label="E-Mail"></v-text-field>
            <v-text-field v-model="editedUser.password" label="Passwort" type="password"></v-text-field>
            <v-select
              v-model="editedUser.roles"
              :items="availableRoles"
              label="Rollen"
              multiple
              chips
            ></v-select>
            <v-btn type="submit" color="primary">Änderungen speichern</v-btn>
          </v-form>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script setup>
import axios from "axios";
</script>

<script>
import axios from "axios";

export default {
  data() {
    return {
      search: '',
      headers: [
        {title: "ID", key: "id"},
        {title: "Firstname", key: "firstname"},
        {title: "Lastname", key: "lastname"},
        {title: "Created", key: "created"},
        {title: "Roles", key: "roles"},
        {title: "Actions", key: "actions"},
      ],
      users: [],
      dialog: false,
      selectedUser: null,
      editedUser: {
        vorname: '',
        nachname: '',
        email: '',
        password: '',
        roles: []
      },
      availableRoles: ["ADMINISTRATOR", "AUDITOR", "AUDITEE", "GAST", "REPORTER", "MANUAL_WRITER"],
    }
  },
  async mounted() {
    try {
      const response = await axios.get("/users/all", {responseType: 'application/json'});
      if (response != null) {
        this.users = response.data;
        console.log(this.users, response)
      } else {
        alert("No users found");
      }
    } catch (e) {
      alert(e);
    }
  },
  methods: {
    openDialog(selectedUser) {
      this.selectedUser = selectedUser;
      this.editedUser = {...selectedUser};
      this.dialog = true;
    },
    saveChanges() {
      // Hier kannst du die Änderungen speichern und den Dialog schließen
      // Beispiel: HTTP-Anfrage an dein Backend, um die Daten zu aktualisieren
      const updatedUser = {
        id: this.selectedUser.id, // Annahme: Der Benutzer hat eine eindeutige ID
        vorname: this.editedUser.vorname,
        nachname: this.editedUser.nachname,
        email: this.editedUser.email,
        password: this.editedUser.password,
        roles: this.editedUser.roles,
      };

      // Beispiel für Axios-Anfrage (du musst dies an deine API anpassen)
      axios.put(`/users/${this.selectedUser.id}`, updatedUser)
        .then(response => {
          // Erfolgreiche Antwort vom Server
          console.log(response.data);
          this.dialog = false; // Schließe den Dialog nach erfolgreicher Aktualisierung
        })
        .catch(error => {
          // Fehler bei der Anfrage
          console.error(error);
          // Handle den Fehler entsprechend (z.B. Fehlermeldung anzeigen)
        });
      this.dialog = false;
    },

    deleteItem(item) {
      // Hier kannst du die Logik für das Löschen implementieren
    },
    initialize() {
      // Hier kannst du die Logik für das Zurücksetzen implementieren
    },
  }
}
</script>
