# Meal Apps

Meal Apps adalah aplikasi Android sederhana yang memungkinkan pengguna untuk menemukan resep makanan, melihat detail resep, dan menyimpan resep favorit ke dalam daftar favorit.

## Fitur

- **Penelusuran Resep**: Pengguna dapat mencari resep makanan berdasarkan kategori, area, atau kata kunci.
- **Detail Resep**: Melihat informasi lengkap tentang suatu resep, termasuk instruksi, bahan-bahan, dan video tutorial jika tersedia.
- **Simpan ke Favorit**: Pengguna dapat menyimpan resep favorit mereka ke dalam daftar favorit.
- **Pencarian Cepat**: Kemampuan untuk melakukan pencarian cepat dan mudah.

## Teknologi dan Library

Aplikasi ini dibangun menggunakan beberapa teknologi dan library:

- **Android Architecture Components**: Menerapkan arsitektur MVVM dengan menggunakan `ViewModel` dan `LiveData`.
- **Room Database**: Menyimpan resep favorit pengguna secara lokal.
- **Retrofit**: Menggunakan Retrofit untuk berkomunikasi dengan API MealDB.
- **Glide**: Memuat dan menampilkan gambar resep.
- **RecyclerView**: Menampilkan daftar resep dan bahan-bahan dalam bentuk list.
- **Coroutine**: Menggunakan coroutine untuk mengelola tugas-tugas asynchronous.
