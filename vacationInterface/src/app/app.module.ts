import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VacationComponent } from './vacation/vacation.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MaterialModule } from './material.module';
import {MatCardModule, MatListModule, MatSelectModule, MatTableModule} from '@angular/material';
import {HttpClientModule} from '@angular/common/http';
import {initializer} from './utils/app-init';
import {KeycloakAngularModule, KeycloakService} from 'keycloak-angular';

@NgModule({
  declarations: [
    AppComponent,
    VacationComponent
  ],
  imports: [
    KeycloakAngularModule,
    MaterialModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    LayoutModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    HttpClientModule,
    MatListModule,
    MatTableModule,
    MatSelectModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
