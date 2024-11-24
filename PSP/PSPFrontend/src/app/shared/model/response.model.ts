export interface RegistrationResponseDto {
  message: string;
  userId: string;
  merchantPassword: string;
}

export interface CreationResponseDto {
  message: string;
  id: string;
}
export interface LoginResponseDto {
  message: string;
  token: string;
}

export interface MessageResponseDto {
  message: string;
}
