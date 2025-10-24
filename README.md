# Responsi-1-Mobile-H1D034063
Muhammad Nabil Putra Monti

1. Inisiasi dan Permintaan API
- Inisiasi ViewModel: Ketika MainActivity dimuat dan menampilkan HomeFragment (Fragment pertama), TeamViewModel dibuat. Di dalam blok init dari TeamViewModel, fungsi fetchTeamData(71) segera dipanggil.
- Pembuatan Request (RetrofitClient): Fungsi fetchTeamData menggunakan RetrofitClient untuk membuat permintaan HTTP GET ke endpoint API spesifik: https://api.football-data.org/v4/teams/71.
- Authentication: Permintaan ini secara otomatis menyertakan header X-Auth-Token yang berisi kunci API Anda (yang diatur di RetrofitClient.kt). Ini diperlukan untuk otorisasi akses data.
- Asynchronous Call: Permintaan dijalankan di latar belakang (background thread) menggunakan Kotlin Coroutines (viewModelScope.launch) untuk memastikan aplikasi tetap responsif (tidak freeze).

2. Penerimaan Data dan Pengolahan (Model & ViewModel)
- Respons API: Setelah API football-data.org menerima, memproses, dan mengotorisasi permintaan, ia mengirimkan kembali data klub Sunderland dalam format JSON.
- Konversi (Gson): Library Gson Converter yang terpasang di Retrofit secara otomatis mengambil data JSON ini dan mengubahnya menjadi objek Kotlin terstruktur (TeamResponse, yang berisi Coach dan list Player). Ini   dilakukan sesuai dengan skema yang Anda definisikan di model/Team.kt.
- Penyimpanan Data (ViewModel): Hasil objek TeamResponse yang berhasil (kode 200 OK) kemudian disimpan ke dalam variabel _teamData: MutableLiveData<TeamResponse?> di dalam TeamViewModel. Jika terjadi kesalahan      (misalnya kode 400/401), error disimpan di _errorMessage.
3. Penyajian di Layar (Fragment)
Data yang tersimpan di LiveData kini siap untuk ditampilkan oleh Fragment di lapisan UI:
- Observing Data: Setiap Fragment (HomeFragment, CoachFragment, SquadFragment) menggunakan fungsi viewModel.teamData.observe(viewLifecycleOwner) { ... } untuk "mengamati" atau mendengarkan perubahan pada data.
- HomeFragment (Profil & Navigasi):
  Data dasar (name, venue, shortName, founded) diambil dari teamData dan ditampilkan di header dan cardSummary.
  Teks sejarah singkat diambil dari resource statis (strings.xml).
  Kartu menu (Coach, Squad, History) menggunakan listener findNavController().navigate() untuk berpindah ke tujuan berikutnya.
- CoachFragment (Detail Pelatih):
  Mengakses objek team.coach dari teamData.
  Mengisi tvCoachName, tvCoachDOB, dan tvCoachNationality dengan data yang diambil, melengkapi desain premium dengan ikon kalender dan bendera statis.
- SquadFragment (Daftar Pemain):
  Mengakses list team.squad dari teamData.
  Data diteruskan ke PlayerAdapter (RecyclerView.Adapter).
  Di dalam Adapter, logika when (player.position) digunakan untuk mengelompokkan sub-posisi (seperti "Left Winger" atau "Centre-Back") menjadi empat kategori utama (Forward, Midfielder, Defender, Goalkeeper).
  Berdasarkan kategori tersebut, CardView diberi warna latar belakang yang sesuai (Merah, Hijau, Biru, Kuning), memenuhi ketentuan responsi.
