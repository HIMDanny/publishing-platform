const SearchField = () => {
  const searchIcon = (
    <svg
      className="absolute left-5 top-1/2 -translate-y-1/2"
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
    >
      <path
        d="M22.6504 21.1773L15.6942 14.2211C16.7736 12.8255 17.3576 11.1193 17.3576 9.32465C17.3576 7.17644 16.5192 5.16215 15.0031 3.6434C13.487 2.12465 11.4674 1.28894 9.32185 1.28894C7.17631 1.28894 5.15667 2.12733 3.6406 3.6434C2.12185 5.15948 1.28613 7.17644 1.28613 9.32465C1.28613 11.4702 2.12453 13.4898 3.6406 15.0059C5.15667 16.5247 7.17363 17.3604 9.32185 17.3604C11.1165 17.3604 12.8201 16.7764 14.2156 15.6997L21.1718 22.6532C21.1922 22.6736 21.2165 22.6898 21.2431 22.7009C21.2698 22.7119 21.2983 22.7176 21.3272 22.7176C21.3561 22.7176 21.3846 22.7119 21.4113 22.7009C21.4379 22.6898 21.4622 22.6736 21.4826 22.6532L22.6504 21.488C22.6708 21.4676 22.687 21.4434 22.6981 21.4168C22.7091 21.3901 22.7148 21.3615 22.7148 21.3327C22.7148 21.3038 22.7091 21.2753 22.6981 21.2486C22.687 21.222 22.6708 21.1977 22.6504 21.1773V21.1773ZM13.5647 13.5675C12.429 14.7005 10.9236 15.3247 9.32185 15.3247C7.72006 15.3247 6.2147 14.7005 5.07899 13.5675C3.94595 12.4318 3.32185 10.9264 3.32185 9.32465C3.32185 7.72287 3.94595 6.21483 5.07899 5.0818C6.2147 3.94876 7.72006 3.32465 9.32185 3.32465C10.9236 3.32465 12.4317 3.94608 13.5647 5.0818C14.6977 6.21751 15.3218 7.72287 15.3218 9.32465C15.3218 10.9264 14.6977 12.4345 13.5647 13.5675Z"
        fill="#F9FAFB"
      />
    </svg>
  );

  return (
    <div className="relative w-full max-w-2xl">
      {searchIcon}
      <input
        autoComplete="off"
        className="w-full rounded-3xl border bg-transparent py-2 pl-14 pr-7 placeholder:text-gray-400"
        type="text"
        name="search"
        id="search"
        placeholder="Search..."
      />
    </div>
  );
};
export default SearchField;
