import classNames from 'classnames';
import { useField } from 'formik';
import InputFieldProps from './input-field-type';

export type TextFieldProps = {
  type: 'text' | 'email';
} & InputFieldProps;

const TextField: React.FC<TextFieldProps> = ({
  type,
  label,
  labelProps,
  ...props
}) => {
  const [field, meta] = useField(props);

  const isError = meta.touched && meta.error;
  return (
    <div>
      <div className="relative">
        <input
          type={type}
          id={field.name}
          className={classNames(
            'peer block w-full appearance-none rounded-md border border-gray-900 bg-transparent px-3 py-4 text-sm text-gray-900 placeholder-shown:border-gray-500 hover:border-gray-400 focus:border-gray-900 focus:outline-none focus:ring-0',
          )}
          placeholder=" "
          {...field}
          {...props}
        />
        <label
          htmlFor={field.name}
          className={classNames(
            'pointer-events-none absolute top-2 left-1 z-10 origin-[0] -translate-y-4 scale-75 transform px-2 text-gray-900 duration-300 peer-placeholder-shown:top-1/2 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:scale-100 peer-placeholder-shown:text-gray-400 peer-focus:top-2 peer-focus:-translate-y-4 peer-focus:scale-75 peer-focus:px-2',
            labelProps?.className ?? '',
          )}
        >
          {label}
        </label>
      </div>
      {isError && (
        <p className={classNames('pr-2 text-right text-sm text-gray-600')}>
          {meta.error}
        </p>
      )}
    </div>
  );
};
export default TextField;
