import PasswordField, { type PasswordFieldProps } from './PasswordField';
import TextField, { type TextFieldProps } from './TextField';

export type FieldProps = TextFieldProps | PasswordFieldProps;

const Field: React.FC<FieldProps> = ({ type, ...props }) => {
  switch (type) {
    case 'text':
    case 'email':
      return (
        <TextField
          type={type}
          {...props}
        />
      );

    case 'password':
      return (
        <PasswordField
          type={type}
          {...props}
        />
      );

    default:
      return (
        <TextField
          type="text"
          {...props}
        />
      );
  }
};
export default Field;
