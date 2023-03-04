import classNames from 'classnames';

type ButtonOriginalProps = React.DetailedHTMLProps<
  React.ButtonHTMLAttributes<HTMLButtonElement>,
  HTMLButtonElement
>;

export type ButtonProps = {
  size?: 'sm' | 'lg';
  variant?: 'contained' | 'outlined';
} & ButtonOriginalProps;

const Button = (props: ButtonProps) => {
  const size = props.size ?? 'sm';

  return (
    <button
      type={props.type || 'button'}
      className={classNames(
        'font-medium',
        {
          'py-4 px-[1.875rem] text-sm': size === 'sm',
          'py-[1.25rem] px-[3.375rem] text-base': size === 'lg',
        },
        {
          'bg-white text-black': props.variant === 'contained',
          'bg-green-700 text-white': props.variant === 'outlined',
        },
      )}
      {...props}
    >
      {props.children}
    </button>
  );
};
export default Button;
