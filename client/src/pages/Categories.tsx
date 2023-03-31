import CategorySectionList from '@features/categories/components/CategorySecitons';
import { useEffect } from 'react';

const CategoriesPage = () => {
  useEffect(() => {
    document.body.classList.add('bg-gray-800');

    return () => {
      document.body.classList.remove('bg-gray-800');
    };
  }, []);

  return (
    <main className="pt-48 text-gray-50">
      <div className="container">
        <h2 className="text-center">Topics</h2>
        <CategorySectionList />
      </div>
    </main>
  );
};
export default CategoriesPage;
