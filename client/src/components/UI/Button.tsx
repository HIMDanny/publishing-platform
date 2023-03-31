import classNames from 'classnames';

export type ButtonProps<C extends React.ElementType> = {
  size?: 'sm' | 'lg';
  variant?: 'primary' | 'secondary';
  type?: 'submit' | 'reset' | 'button';
  text: string;
  component?: C;
} & React.ComponentPropsWithRef<C>;

const Button = <C extends React.ElementType = 'button'>({
  size = 'sm',
  variant = 'primary',
  text,
  type,
  component,
  ...props
}: ButtonProps<C>) => {
  const ButtonTag = component ?? 'button';

  return (
    <ButtonTag
      type={type ?? 'button'}
      className={classNames(
        'rounded-full font-medium transition-colors hover:bg-green-600 hover:text-gray-50 active:bg-green-500 disabled:border disabled:border-solid disabled:border-gray-400 disabled:bg-transparent disabled:text-gray-400',
        {
          'py-4 px-[1.875rem] text-sm': size === 'sm',
          'py-[1.25rem] px-[3.375rem] text-base': size === 'lg',
        },
        {
          'bg-white text-black': variant === 'primary',
          'bg-green-700 text-gray-50': variant === 'secondary',
        },
      )}
      {...props}
    >
      {text}
    </ButtonTag>
  );
};
export default Button;
