import classNames from 'classnames';

type ButtonOriginalProps = React.DetailedHTMLProps<
  React.ButtonHTMLAttributes<HTMLButtonElement>,
  HTMLButtonElement
>;

export type ButtonProps = {
  size?: 'sm' | 'lg';
  variant?: 'primary' | 'secondary';
  type?: ButtonOriginalProps['type'];
  children: React.ReactNode;
};

const Button = ({
  size = 'sm',
  variant = 'primary',
  ...props
}: ButtonProps) => {
  return (
    <button
      type={props.type || 'button'}
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
      {props.children}
    </button>
  );
};
export default Button;
