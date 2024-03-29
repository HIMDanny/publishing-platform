export type SliderArrowProps = {
  mode: 'prev' | 'next';
  className?: string;
  style?: React.CSSProperties;
  onClick?: () => void;
};

const SliderArrow = ({ mode, className, onClick, style }: SliderArrowProps) => {
  const prevArrow = (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="44"
      height="45"
      viewBox="0 0 44 45"
      fill="none"
    >
      <g clipPath="url(#clip0_139_825)">
        <path
          d="M26.4813 13.4407L14.4009 22.1818C14.3507 22.218 14.3099 22.2656 14.2817 22.3206C14.2535 22.3757 14.2388 22.4367 14.2388 22.4986C14.2388 22.5604 14.2535 22.6214 14.2817 22.6765C14.3099 22.7315 14.3507 22.7791 14.4009 22.8153L26.4813 31.5564C26.7416 31.743 27.105 31.5564 27.105 31.2372V28.934C27.105 28.4332 26.8643 27.9568 26.4567 27.6622L19.3166 22.501L26.4567 17.3349C26.8643 17.0403 27.105 16.5689 27.105 16.0631V13.7599C27.105 13.4407 26.7416 13.2541 26.4813 13.4407Z"
          fill="#374151"
        />
        <path
          d="M22 0.5C9.85089 0.5 0 10.3509 0 22.5C0 34.6491 9.85089 44.5 22 44.5C34.1491 44.5 44 34.6491 44 22.5C44 10.3509 34.1491 0.5 22 0.5ZM22 40.7679C11.9134 40.7679 3.73214 32.5866 3.73214 22.5C3.73214 12.4134 11.9134 4.23214 22 4.23214C32.0866 4.23214 40.2679 12.4134 40.2679 22.5C40.2679 32.5866 32.0866 40.7679 22 40.7679Z"
          fill="#374151"
        />
      </g>
      <defs>
        <clipPath id="clip0_139_825">
          <rect
            width="44"
            height="44"
            fill="white"
            transform="translate(0 0.5)"
          />
        </clipPath>
      </defs>
    </svg>
  );
  const nextArrow = (
    <svg
      width="44"
      height="45"
      viewBox="0 0 44 45"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M17.5187 31.5593L29.5991 22.8182C29.6493 22.782 29.6901 22.7344 29.7183 22.6794C29.7465 22.6243 29.7612 22.5633 29.7612 22.5014C29.7612 22.4396 29.7465 22.3786 29.7183 22.3235C29.6901 22.2685 29.6493 22.2209 29.5991 22.1847L17.5187 13.4436C17.2584 13.257 16.895 13.4436 16.895 13.7628L16.895 16.066C16.895 16.5669 17.1357 17.0432 17.5433 17.3378L24.6834 22.499L17.5433 27.6651C17.1357 27.9597 16.895 28.4311 16.895 28.9369L16.895 31.2401C16.895 31.5593 17.2584 31.7459 17.5187 31.5593Z"
        fill="#374151"
      />
      <path
        d="M22 44.5C34.1491 44.5 44 34.6491 44 22.5C44 10.3509 34.1491 0.5 22 0.500001C9.85089 0.500002 -4.1179e-06 10.3509 -3.05579e-06 22.5C-1.99368e-06 34.6491 9.85089 44.5 22 44.5ZM22 4.23214C32.0866 4.23214 40.2679 12.4134 40.2679 22.5C40.2679 32.5866 32.0866 40.7679 22 40.7679C11.9134 40.7679 3.73214 32.5866 3.73214 22.5C3.73214 12.4134 11.9134 4.23214 22 4.23214Z"
        fill="#374151"
      />
    </svg>
  );

  return (
    <div
      className={className}
      style={style}
      onClick={onClick}
    >
      {mode === 'prev' ? prevArrow : nextArrow}
    </div>
  );
};
export default SliderArrow;
