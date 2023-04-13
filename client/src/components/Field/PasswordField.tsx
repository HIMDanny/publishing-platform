import classNames from 'classnames';
import { useField } from 'formik';
import InputFieldProps from './input-field-type';
import { useState } from 'react';

export type PasswordFieldProps = {
  type: 'password';
} & InputFieldProps;

const PasswordField: React.FC<PasswordFieldProps> = ({
  type,
  label,
  labelProps,
  ...props
}) => {
  const [field, meta] = useField(props);
  const [isPasswordVisible, setIsPasswordVisible] = useState(false);
  const passwordVisibilityHandler = () => setIsPasswordVisible((prev) => !prev);

  const inputType = isPasswordVisible ? 'text' : type;
  const isError = meta.touched && meta.error;
  return (
    <div>
      <div className="relative">
        <input
          type={inputType}
          id={field.name}
          className={classNames(
            'peer block w-full appearance-none rounded-md border bg-transparent px-3 py-4 text-sm focus:outline-none focus:ring-0',
            {
              'border-gray-900 text-gray-900 placeholder-shown:border-gray-500 hover:border-gray-400 focus:border-gray-900':
                !isError,
            },
            { 'border-error text-error hover:border-opacity-50': isError },
          )}
          placeholder=" "
          {...field}
          {...props}
        />
        <label
          htmlFor={field.name}
          className={classNames(
            'pointer-events-none absolute top-2 left-1 z-10 origin-[0] -translate-y-4 scale-75 transform px-2  duration-300 peer-placeholder-shown:top-1/2 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:scale-100  peer-focus:top-2 peer-focus:-translate-y-4 peer-focus:scale-75 peer-focus:px-2',
            { 'text-gray-900 peer-placeholder-shown:text-gray-400': !isError },
            { 'text-error': isError },
            labelProps?.className ?? '',
          )}
        >
          {label}
        </label>
        <EyeIcon
          isPasswordVisible={isPasswordVisible}
          onVisibilityChange={passwordVisibilityHandler}
        />
      </div>
      {isError && (
        <p
          className={classNames('pr-2 text-right text-sm text-gray-600', {
            'text-error': isError,
          })}
        >
          {meta.error}
        </p>
      )}
    </div>
  );
};
export default PasswordField;

function EyeIcon({
  isPasswordVisible,
  onVisibilityChange,
}: {
  isPasswordVisible: boolean;
  onVisibilityChange: () => void;
}) {
  const eyeVisiblePath = (
    <path
      d="M25.4837 12.2532C22.7328 6.45829 18.5745 3.54199 13.0002 3.54199C7.42294 3.54199 3.26758 6.45829 0.516686 12.2561C0.406346 12.4897 0.349121 12.7449 0.349121 13.0033C0.349121 13.2617 0.406346 13.5168 0.516686 13.7505C3.26758 19.5453 7.42584 22.4616 13.0002 22.4616C18.5774 22.4616 22.7328 19.5453 25.4837 13.7476C25.7071 13.2775 25.7071 12.7319 25.4837 12.2532V12.2532ZM13.0002 20.3723C8.31959 20.3723 4.89258 17.9987 2.47539 13.0018C4.89258 8.00494 8.31959 5.63128 13.0002 5.63128C17.6807 5.63128 21.1078 8.00494 23.5249 13.0018C21.1107 17.9987 17.6836 20.3723 13.0002 20.3723ZM12.8841 7.89467C10.0636 7.89467 7.77695 10.1813 7.77695 13.0018C7.77695 15.8223 10.0636 18.109 12.8841 18.109C15.7046 18.109 17.9912 15.8223 17.9912 13.0018C17.9912 10.1813 15.7046 7.89467 12.8841 7.89467ZM12.8841 16.2518C11.0879 16.2518 9.6341 14.798 9.6341 13.0018C9.6341 11.2056 11.0879 9.75181 12.8841 9.75181C14.6803 9.75181 16.1341 11.2056 16.1341 13.0018C16.1341 14.798 14.6803 16.2518 12.8841 16.2518Z"
      fill="currentColor"
      fillOpacity="0.85"
    />
  );

  const eyeInvisiblePath = (
    <>
      <path
        d="M25.4837 12.2485C24.4636 10.0995 23.2497 8.34681 21.8419 6.99051L20.3655 8.46694C21.5694 9.6175 22.6181 11.1227 23.5249 12.9972C21.1107 17.9941 17.6836 20.3677 13.0002 20.3677C11.5943 20.3677 10.2995 20.1513 9.11555 19.7183L7.51579 21.3181C9.16014 22.0774 10.9883 22.457 13.0002 22.457C18.5774 22.457 22.7386 19.5523 25.4837 13.743C25.594 13.5093 25.6512 13.2541 25.6512 12.9958C25.6512 12.7374 25.594 12.4822 25.4837 12.2485V12.2485ZM23.639 2.94426L22.402 1.70578C22.3804 1.6842 22.3548 1.66707 22.3266 1.65539C22.2984 1.64371 22.2682 1.6377 22.2377 1.6377C22.2072 1.6377 22.177 1.64371 22.1488 1.65539C22.1206 1.66707 22.095 1.6842 22.0735 1.70578L18.8998 4.87801C17.15 3.98426 15.1835 3.53739 13.0002 3.53739C7.42294 3.53739 3.26177 6.44208 0.516686 12.2515C0.406346 12.4851 0.349121 12.7403 0.349121 12.9987C0.349121 13.257 0.406346 13.5122 0.516686 13.7459C1.61337 16.0557 2.93368 17.9071 4.47762 19.3002L1.40695 22.37C1.36345 22.4135 1.33901 22.4725 1.33901 22.5341C1.33901 22.5956 1.36345 22.6546 1.40695 22.6982L2.64573 23.9369C2.68926 23.9804 2.74828 24.0049 2.80982 24.0049C2.87136 24.0049 2.93039 23.9804 2.97392 23.9369L23.639 3.27274C23.6606 3.25118 23.6777 3.22558 23.6894 3.1974C23.7011 3.16922 23.7071 3.13901 23.7071 3.1085C23.7071 3.078 23.7011 3.04779 23.6894 3.01961C23.6777 2.99142 23.6606 2.96582 23.639 2.94426ZM2.47539 12.9972C4.89258 8.00033 8.31959 5.62667 13.0002 5.62667C14.5828 5.62667 16.0218 5.89828 17.3273 6.45049L15.2874 8.49045C14.3213 7.97498 13.2151 7.78368 12.132 7.94475C11.0489 8.10583 10.0462 8.61075 9.27193 9.38504C8.49764 10.1593 7.99271 11.162 7.83164 12.2451C7.67056 13.3282 7.86187 14.4344 8.37733 15.4005L5.95666 17.8211C4.61691 16.6387 3.462 15.0366 2.47539 12.9972ZM9.6341 12.9972C9.6346 12.4855 9.75581 11.9812 9.98785 11.5251C10.2199 11.0691 10.5563 10.6742 10.9696 10.3726C11.3829 10.0709 11.8616 9.87106 12.3667 9.78918C12.8718 9.70729 13.389 9.7457 13.8765 9.90129L9.78818 13.9896C9.68571 13.6688 9.63373 13.334 9.6341 12.9972V12.9972Z"
        fill="currentColor"
        fillOpacity="0.85"
      />
      <path
        d="M12.8846 16.252C12.7842 16.252 12.6852 16.2474 12.5872 16.2384L11.0544 17.7711C11.9763 18.1242 12.9807 18.2026 13.9462 17.997C14.9117 17.7914 15.797 17.3105 16.495 16.6125C17.1931 15.9144 17.6739 15.0292 17.8796 14.0636C18.0852 13.0981 18.0067 12.0937 17.6537 11.1719L16.121 12.7046C16.13 12.8027 16.1346 12.9016 16.1346 13.002C16.1348 13.4289 16.0509 13.8516 15.8877 14.246C15.7244 14.6404 15.485 14.9988 15.1832 15.3006C14.8814 15.6025 14.523 15.8419 14.1286 16.0051C13.7342 16.1684 13.3115 16.2523 12.8846 16.252V16.252Z"
        fill="currentColor"
        fillOpacity="0.85"
      />
    </>
  );

  return (
    <button
      type="button"
      onClick={onVisibilityChange}
      className="absolute right-4 top-1/2 -translate-y-1/2"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="26"
        height="26"
        viewBox="0 0 26 26"
        fill="none"
        className="text-gray-700"
      >
        {isPasswordVisible ? eyeVisiblePath : eyeInvisiblePath}
      </svg>
    </button>
  );
}
