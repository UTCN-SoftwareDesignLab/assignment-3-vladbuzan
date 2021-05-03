import React from 'react';
import { CookiesProvider } from 'react-cookie';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route } from 'react-router-dom';
import AdminPage from './navigation/AdminPage';
import LandingPage from './navigation/Landing';
import MedicPage from './navigation/MedicPage';
import SecretaryPage from './navigation/SecretaryPage';
import 'semantic-ui-css/semantic.min.css'

ReactDOM.render(
  <BrowserRouter>
    <CookiesProvider>
      <Route exact path='/'>
        <LandingPage />
      </Route>
      <Route exact path='/admin'>
        <AdminPage />
      </Route>
      <Route exact path='/secretary'>
        <SecretaryPage />
      </Route>
      <Route exact path='/medic'>
        <MedicPage />
      </Route>
    </CookiesProvider>
  </BrowserRouter>,
  document.getElementById('root')
);

