import { NgModule } from '@angular/core';
import {
  MatMenuModule,
  MatIconModule,
  MatButtonModule,
  MatDialogModule,
  MatInputModule,
  MatSnackBarModule,
  MatProgressBarModule,
  MAT_DIALOG_DEFAULT_OPTIONS
} from '@angular/material';

@NgModule({
  imports: [
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    MatSnackBarModule,
    MatProgressBarModule
  ],
  exports: [
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    MatSnackBarModule,
    MatProgressBarModule
  ],
  providers: [
    {
      provide: MAT_DIALOG_DEFAULT_OPTIONS,
      useValue: { closeOnNavigation: true, hasBackdrop: true, autoFocus: true }
    }
  ]
})
export class MaterialModule {}
